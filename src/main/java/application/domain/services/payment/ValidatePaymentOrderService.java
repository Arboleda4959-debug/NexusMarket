package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Payment;
import java.util.Objects;

public class ValidatePaymentOrderService {
    public void execute(Order order, Payment payment) {
        if (order == null) throw new DomainException("Order is required.");
        if (payment == null) throw new DomainException("Payment is required.");
        if (blank(order.getId()) || blank(payment.getOrderId())) throw new DomainException("Order and payment identifiers are required.");
        if (!Objects.equals(payment.getOrderId(), order.getId())) throw new DomainException("Payment does not belong to the specified order.");
        if (order.getTotal() == null || payment.getAmount() == null) throw new DomainException("Order total and payment amount are required.");
        if (!order.getTotal().currency().equals(payment.getAmount().currency())) throw new DomainException("Payment currency must match order currency.");
        if (order.getTotal().amount().compareTo(payment.getAmount().amount()) != 0) throw new DomainException("Payment amount must match the order total.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
