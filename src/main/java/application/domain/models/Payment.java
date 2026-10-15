package application.domain.models;

import application.domain.valueobjects.Money;
import application.domain.valueobjects.PaymentStatus;
import java.time.LocalDateTime;

public class Payment {
    private final String id;
    private final String orderId;
    private final Money amount;
    private PaymentStatus status;
    private final LocalDateTime processedAt;

    public Payment(String id, String orderId, Money amount, PaymentStatus status, LocalDateTime processedAt) {
        this.id = require(id, "id");
        this.orderId = require(orderId, "orderId");
        this.amount = java.util.Objects.requireNonNull(amount, "amount is required");
        this.status = java.util.Objects.requireNonNull(status, "status is required");
        this.processedAt = processedAt;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public Money getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
