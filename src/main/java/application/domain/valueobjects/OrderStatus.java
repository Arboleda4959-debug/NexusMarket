package application.domain.valueobjects;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAID,
    PREPARING,
    SHIPPED,
    DELIVERED,
    COMPLETED,
    CANCELLED
}
