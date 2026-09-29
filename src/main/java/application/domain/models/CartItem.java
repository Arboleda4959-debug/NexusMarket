package application.domain.models;

import application.domain.valueobjects.ProductPrice;
import application.domain.valueobjects.Quantity;
import java.math.BigDecimal;

public class CartItem {
    private final String productId;
    private final Quantity quantity;
    private final ProductPrice unitPrice;

    public CartItem(String productId, Quantity quantity, ProductPrice unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() { return productId; }
    public Quantity getQuantity() { return quantity; }
    public ProductPrice getUnitPrice() { return unitPrice; }

    public BigDecimal getSubtotal() {
        return unitPrice.amount().multiply(BigDecimal.valueOf(quantity.value()));
    }
}
