package application.domain.models;

import application.domain.valueobjects.Money;
import java.time.LocalDateTime;

public class Invoice {
    private final String id;
    private final String orderId;
    private final String number;
    private final Money total;
    private final LocalDateTime issuedAt;

    public Invoice(String id, String orderId, String number, Money total, LocalDateTime issuedAt) {
        this.id = require(id, "id");
        this.orderId = require(orderId, "orderId");
        this.number = require(number, "number");
        this.total = java.util.Objects.requireNonNull(total, "total is required");
        this.issuedAt = java.util.Objects.requireNonNull(issuedAt, "issuedAt is required");
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getNumber() { return number; }
    public Money getTotal() { return total; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
