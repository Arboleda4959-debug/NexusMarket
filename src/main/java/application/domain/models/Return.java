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
        this.id = id;
        this.orderId = orderId;
        this.reason = reason;
        this.status = status;
        this.requestedAt = requestedAt;
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
}