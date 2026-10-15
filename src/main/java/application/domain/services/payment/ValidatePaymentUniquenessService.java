package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.models.Payment;
import application.domain.ports.out.PaymentRepositoryPort;
import application.domain.valueobjects.PaymentStatus;
import java.util.List;
import java.util.Objects;

/** Prevents a second live/successful payment for the same order. */
public class ValidatePaymentUniquenessService {
    private final PaymentRepositoryPort payments;
    public ValidatePaymentUniquenessService(PaymentRepositoryPort payments) { this.payments = Objects.requireNonNull(payments); }
    public void execute(String orderId) {
        if (orderId == null || orderId.isBlank()) throw new DomainException("Order identifier is required.");
        List<Payment> existing = payments.findByOrderId(orderId);
        if (existing == null) throw new DomainException("Payment repository returned an invalid result.");
        for (Payment payment : existing) {
            if (payment == null || payment.getStatus() == null || payment.getOrderId() == null) throw new DomainException("Payment history contains an invalid record.");
            if (!orderId.equals(payment.getOrderId())) throw new DomainException("Payment repository returned a payment for a different order.");
            if (payment.getStatus() == PaymentStatus.PENDING || payment.getStatus() == PaymentStatus.APPROVED || payment.getStatus() == PaymentStatus.REFUNDED)
                throw new DomainException("This order already has a pending or successful payment.");
        }
    }
}
