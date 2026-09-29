package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Payment;

public class ValidatePaymentOrderService {

    public void execute(
            Order order,
            Payment payment
    ) {
        if (order == null) {
            throw new DomainException("Order is required.");
        }

        if (payment == null) {
            throw new DomainException("Payment is required.");
        }

        if (!payment.getOrderId().equals(order.getId())) {
            throw new DomainException(
                    "Payment does not belong to the specified order."
            );
        }
    }
}