package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.models.Payment;
import application.domain.ports.out.PaymentRepositoryPort;
import java.util.Optional;
import application.domain.valueobjects.Money;
import application.domain.valueobjects.PaymentStatus;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ValidatePaymentUniquenessServiceTest {
    private Payment payment(PaymentStatus status) { return new Payment("pay-1", "order-1", new Money(new BigDecimal("10"), "COP"), status, LocalDateTime.now()); }
    private PaymentRepositoryPort repository(List<Payment> values) {
        return new PaymentRepositoryPort() {
            public Optional<Payment> findById(String id) { return Optional.empty(); }
            public List<Payment> findByOrderId(String id) { return values; }
            public Payment save(Payment payment) { return payment; }
        };
    }
    @Test void permitsOrderWithNoPayments() { assertDoesNotThrow(() -> new ValidatePaymentUniquenessService(repository(List.of())).execute("order-1")); }
    @Test void rejectsExistingApprovedPayment() { assertThrows(DomainException.class, () -> new ValidatePaymentUniquenessService(repository(List.of(payment(PaymentStatus.APPROVED)))).execute("order-1")); }
    @Test void allowsRetryAfterRejectedPayment() { assertDoesNotThrow(() -> new ValidatePaymentUniquenessService(repository(List.of(payment(PaymentStatus.REJECTED)))).execute("order-1")); }
}
