package application.domain.services.order;

import application.domain.exceptions.DomainException;
import application.domain.valueobjects.OrderStatus;

public class ValidateOrderStatusTransitionService {

    public void execute(OrderStatus currentStatus, OrderStatus newStatus) {

        if (currentStatus == null || newStatus == null) {
            throw new DomainException(
                    "Order statuses must be provided."
            );
        }

        boolean valid = switch (currentStatus) {
            case CREATED ->
                    newStatus == OrderStatus.PAYMENT_PENDING
                    || newStatus == OrderStatus.CANCELLED;

            case PAYMENT_PENDING ->
                    newStatus == OrderStatus.PAID
                    || newStatus == OrderStatus.CANCELLED;

            case PAID ->
                    newStatus == OrderStatus.PREPARING;

            case PREPARING ->
                    newStatus == OrderStatus.SHIPPED;

            case SHIPPED ->
                    newStatus == OrderStatus.DELIVERED;

            case DELIVERED ->
                    newStatus == OrderStatus.COMPLETED;

            case COMPLETED, CANCELLED -> false;
        };

        if (!valid) {
            throw new DomainException(
                    "Invalid order status transition from "
                            + currentStatus + " to " + newStatus
            );
        }
    }
}