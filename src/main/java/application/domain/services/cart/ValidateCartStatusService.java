package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Cart;

public class ValidateCartStatusService {

    public void execute(Cart cart) {

        if (cart == null) {
            throw new DomainException("Cart is required.");
        }

        if (cart.getStatus() == null) {
            throw new DomainException("Cart status is required.");
        }
    }
}