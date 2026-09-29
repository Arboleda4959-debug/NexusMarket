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
        this.id = id;
        this.returnId = returnId;
        this.amount = amount;
        this.status = status;
        this.processedAt = processedAt;
    }

    public String getId() { return id; }
    public String getReturnId() { return returnId; }
    public Money getAmount() { return amount; }
    public RefundStatus getStatus() { return status; }
    public LocalDateTime getProcessedAt() { return processedAt; }
}
