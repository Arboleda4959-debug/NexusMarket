package application.domain.models;

import application.domain.valueobjects.Money;
import application.domain.valueobjects.RefundStatus;
import java.time.LocalDateTime;

public class Refund {
    private final String id;
    private final String returnId;
    private final Money amount;
    private RefundStatus status;
    private final LocalDateTime processedAt;

    public Refund(String id, String returnId, Money amount,
                  RefundStatus status, LocalDateTime processedAt) {
        this.id = require(id, "id");
        this.returnId = require(returnId, "returnId");
        this.amount = java.util.Objects.requireNonNull(amount, "amount is required");
        this.status = java.util.Objects.requireNonNull(status, "status is required");
        this.processedAt = processedAt;
    }

    public String getId() { return id; }
    public String getReturnId() { return returnId; }
    public Money getAmount() { return amount; }
    public RefundStatus getStatus() { return status; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
