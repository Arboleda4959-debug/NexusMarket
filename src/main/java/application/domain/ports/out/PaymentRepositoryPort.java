package application.domain.ports.out;

import application.domain.models.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {
    Optional<Payment> findById(String paymentId);
    List<Payment> findByOrderId(String orderId);
    Payment save(Payment payment);
}
