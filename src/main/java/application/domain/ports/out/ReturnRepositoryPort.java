package application.domain.ports.out;

import application.domain.models.Return;
import java.util.List;
import java.util.Optional;

public interface ReturnRepositoryPort {
    Optional<Return> findById(String returnId);
    List<Return> findByOrderId(String orderId);
    Return save(Return returnRequest);
}
