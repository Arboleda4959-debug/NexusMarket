package application.domain.valueobjects;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductPrice(BigDecimal amount, String currency) {
    public ProductPrice {
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be blank");
        }
        currency = currency.trim().toUpperCase();
    }
}
