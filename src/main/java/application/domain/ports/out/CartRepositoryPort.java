package application.domain.ports.out;

import application.domain.models.Cart;
import java.util.Optional;

public interface CartRepositoryPort {
    Optional<Cart> findById(String cartId);
    Optional<Cart> findActiveByBuyerId(String buyerId);
    Cart save(Cart cart);
}
