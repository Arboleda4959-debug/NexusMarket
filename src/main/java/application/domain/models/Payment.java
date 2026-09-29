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
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.processedAt = processedAt;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public Money getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getProcessedAt() { return processedAt; }
}
