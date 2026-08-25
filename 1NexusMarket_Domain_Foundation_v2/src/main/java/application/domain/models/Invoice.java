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
        this.id = id;
        this.orderId = orderId;
        this.number = number;
        this.total = total;
        this.issuedAt = issuedAt;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getNumber() { return number; }
    public Money getTotal() { return total; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
}
