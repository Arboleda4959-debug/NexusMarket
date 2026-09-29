package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Cart;

import java.math.BigDecimal;

public class CalculateCartTotalService {

    public BigDecimal execute(Cart cart) {

        if (cart == null) {
            throw new DomainException("Cart is required.");
        }

        return cart.getItems()
                .stream()
                .map(item -> item.getSubtotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}