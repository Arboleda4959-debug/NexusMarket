package application.domain.services.returns;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Return;

public class ValidateReturnOrderService {

    public void execute(
            Order order,
            Return returnRequest
    ) {
        if (order == null) {
            throw new DomainException("Order is required.");
        }

        if (returnRequest == null) {
            throw new DomainException("Return is required.");
        }

        if (!returnRequest.getOrderId().equals(order.getId())) {
            throw new DomainException(
                    "Return does not belong to the specified order."
            );
        }
    }
}