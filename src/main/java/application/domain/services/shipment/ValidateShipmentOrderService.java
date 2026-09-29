package application.domain.services.shipment;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Shipment;

public class ValidateShipmentOrderService {

    public void execute(
            Order order,
            Shipment shipment
    ) {
        if (order == null) {
            throw new DomainException("Order is required.");
        }

        if (shipment == null) {
            throw new DomainException("Shipment is required.");
        }

        if (!shipment.getOrderId().equals(order.getId())) {
            throw new DomainException(
                    "Shipment does not belong to the specified order."
            );
        }
    }
}