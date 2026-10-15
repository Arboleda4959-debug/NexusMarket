package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Cart;
import application.domain.models.CartItem;
import application.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CartValidationServicesTest {
    @Test void calculatesTotalForSameCurrency() {
        Cart cart = new Cart("cart-1", "buyer-1", List.of(
                new CartItem("p1", new Quantity(2), new ProductPrice(new BigDecimal("5.00"), "COP")),
                new CartItem("p2", new Quantity(1), new ProductPrice(new BigDecimal("3.00"), "COP"))), CartStatus.ACTIVE);
        assertEquals(new BigDecimal("13.00"), new CalculateCartTotalService().execute(cart));
    }
    @Test void rejectsMixedCurrencies() {
        Cart cart = new Cart("cart-1", "buyer-1", List.of(
                new CartItem("p1", new Quantity(1), new ProductPrice(new BigDecimal("5"), "COP")),
                new CartItem("p2", new Quantity(1), new ProductPrice(new BigDecimal("3"), "USD"))), CartStatus.ACTIVE);
        assertThrows(DomainException.class, () -> new CalculateCartTotalService().execute(cart));
    }
    @Test void checkoutRequiresActiveNonEmptyCart() {
        var service = new ValidateCartStatusService();
        assertThrows(DomainException.class, () -> service.validateCanCheckout(new Cart("cart-1", "buyer-1", List.of(), CartStatus.ACTIVE)));
        assertThrows(DomainException.class, () -> service.validateCanModify(new Cart("cart-2", "buyer-1", List.of(), CartStatus.CONVERTED)));
    }
}
