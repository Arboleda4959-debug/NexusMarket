package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Cart;
import application.domain.valueobjects.CartStatus;

public class ValidateCartStatusService {
    public void execute(Cart cart) {
        if (cart == null) throw new DomainException("Cart is required.");
        if (cart.getStatus() == null) throw new DomainException("Cart status is required.");
    }
    public void validateCanModify(Cart cart) {
        execute(cart);
        if (cart.getStatus() != CartStatus.ACTIVE) throw new DomainException("Only an active cart can be modified.");
    }
    public void validateCanCheckout(Cart cart) {
        validateCanModify(cart);
        if (cart.getItems().isEmpty()) throw new DomainException("An empty cart cannot be checked out.");
    }
}
