package application.domain.ports.out;

import application.domain.models.Refund;
import java.util.List;
import java.util.Optional;

/** Output port for refund history and persistence. Implementations must enforce atomic/idempotent creation. */
public interface RefundRepositoryPort {
    List<Refund> findByReturnId(String returnId);
    Optional<Refund> findById(String refundId);
    Refund save(Refund refund);
}
