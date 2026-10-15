package application.domain.ports.out;

import application.domain.models.Order;
import java.util.Optional;

public interface OrderRepositoryPort {
    Optional<Order> findById(String orderId);
    Order save(Order order);
}
