package application.domain.models;

import application.domain.valueobjects.ReturnStatus;
import java.time.LocalDateTime;

public class Return {

    private final String id;
    private final String orderId;
    private final String reason;
    private ReturnStatus status;
    private final LocalDateTime requestedAt;

    public Return(
            String id,
            String orderId,
            String reason,
            ReturnStatus status,
            LocalDateTime requestedAt
    ) {
        this.id = require(id, "id");
        this.orderId = require(orderId, "orderId");
        this.reason = require(reason, "reason");
        this.status = java.util.Objects.requireNonNull(status, "status is required");
        this.requestedAt = java.util.Objects.requireNonNull(requestedAt, "requestedAt is required");
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}