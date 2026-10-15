package application.domain.models;

import application.domain.valueobjects.ProductPrice;
import application.domain.valueobjects.Quantity;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private final String productId; private final Quantity quantity; private final ProductPrice unitPrice;
    public OrderItem(String productId, Quantity quantity, ProductPrice unitPrice) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("productId cannot be blank");
        this.productId = productId.trim(); this.quantity = Objects.requireNonNull(quantity, "Quantity is required");
        if (quantity.value() <= 0) throw new IllegalArgumentException("Order item quantity must be greater than zero");
        this.unitPrice = Objects.requireNonNull(unitPrice, "Unit price is required");
    }
    public String getProductId() { return productId; }
    public Quantity getQuantity() { return quantity; }
    public ProductPrice getUnitPrice() { return unitPrice; }
    public BigDecimal getSubtotal() { return unitPrice.amount().multiply(BigDecimal.valueOf(quantity.value())); }
}
