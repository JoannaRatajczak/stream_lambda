package pl.javastart.streamsexercise;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class PaymentService {

    private PaymentRepository paymentRepository;
    private DateTimeProvider dateTimeProvider;

    PaymentService(PaymentRepository paymentRepository, DateTimeProvider dateTimeProvider) {
        this.paymentRepository = paymentRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    List<Payment> findPaymentsSortedByDateDesc() {

        List<Payment> list = paymentRepository.findAll();
        List<Payment> sorted = list.stream()
                .sorted(Comparator.comparing(Payment::getPaymentDate)
                        .reversed())
                .collect(Collectors.toList());

        return sorted;
    }

    List<Payment> findPaymentsForCurrentMonth() {

        YearMonth month = dateTimeProvider.yearMonthNow();

        List<Payment> list = paymentRepository.findAll();
        List<Payment> currentList = list.stream()
                .filter(d -> d.getPaymentDate().getMonth() == month.getMonth() && d.getPaymentDate().getYear() == month.getYear())
                .collect(Collectors.toList());

        return currentList;
    }

    List<Payment> findPaymentsForGivenMonth(YearMonth yearMonth) {

        List<Payment> list = paymentRepository.findAll();
        List<Payment> currentList=list.stream()
                .filter(d -> d.getPaymentDate().getMonth() == yearMonth.getMonth() && d.getPaymentDate().getYear() == yearMonth.getYear())
                .collect(Collectors.toList());

        return currentList;


    }

    List<Payment> findPaymentsForGivenLastDays(int days) {

        List<Payment> list = paymentRepository.findAll();

        List<Payment> currentList =list.stream()
                .filter((Payment p) -> p.getPaymentDate().isBefore(p.getPaymentDate().minusDays(days)))
                .collect(Collectors.toList());

        return currentList;
    }

    Set<Payment> findPaymentsWithOnePaymentItem() {

        List<Payment> list = paymentRepository.findAll();

        Set<Payment> set = list.stream()
                .filter(p -> p.getPaymentItems().size() == 1)
                .collect(Collectors.toSet());

        return set;
    }
//
//    Set<String> findProductsSoldInCurrentMonth() {
//        List<Payment> list = paymentRepository.findAll();
//        ZonedDateTime currMonth = dateTimeProvider.zonedDateTimeNow();
//
////
////        Set<String> set = list.stream()
////                .filter(p -> p.getPaymentDate().getMonth() == currMonth.getMonth() && p.getPaymentDate().getYear() == currMonth.getYear())
////                .map(giveMeItemName(p))
////                .collect(Collectors.toList());
//
//        return null;
//
//    }

//    BigDecimal sumTotalForGivenMonth(YearMonth yearMonth) {
//
//        List<Payment> list = paymentRepository.findAll();
//        BigDecimal sum = list.stream()
//                .filter(d -> d.getPaymentDate().getMonth() == yearMonth.getMonth() && d.getPaymentDate().getYear() == yearMonth.getYear())
//                .map(x -> x.getPaymentItems())
//                .reduce(0, Integer::sum);
//
//
//        return sum;
//    }
//
//    BigDecimal sumDiscountForGivenMonth(YearMonth yearMonth) {
//        throw new RuntimeException("Not implemented");
//    }
//
//    List<PaymentItem> getPaymentsForUserWithEmail(String userEmail) {
//        throw new RuntimeException("Not implemented");
//    }
//
//    Set<Payment> findPaymentsWithValueOver(int value) {
//        throw new RuntimeException("Not implemented");
//    }




}


