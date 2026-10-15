package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Cart;
import application.domain.models.CartItem;
import application.domain.valueobjects.ProductPrice;
import application.domain.valueobjects.Quantity;
import java.math.BigDecimal;

public class CalculateCartTotalService {
    public BigDecimal execute(Cart cart) {
        if (cart == null) throw new DomainException("Cart is required.");
        BigDecimal total = BigDecimal.ZERO;
        String currency = null;
        for (CartItem item : cart.getItems()) {
            if (item == null) throw new DomainException("Cart cannot contain a null item.");
            Quantity quantity = item.getQuantity();
            ProductPrice price = item.getUnitPrice();
            if (quantity == null || price == null) throw new DomainException("Cart item quantity and unit price are required.");
            if (quantity.value() <= 0) throw new DomainException("Cart item quantity must be greater than zero.");
            if (price.amount() == null || price.currency() == null) throw new DomainException("Cart item price is invalid.");
            if (currency == null) currency = price.currency();
            else if (!currency.equals(price.currency())) throw new DomainException("Cart items must use the same currency.");
            total = total.add(item.getSubtotal());
        }
        return total;
    }
}
