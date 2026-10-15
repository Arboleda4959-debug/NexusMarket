package application.domain.services.shipment;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Shipment;
import java.util.Objects;

public class ValidateShipmentOrderService {
    public void execute(Order order, Shipment shipment) {
        if (order == null) throw new DomainException("Order is required.");
        if (shipment == null) throw new DomainException("Shipment is required.");
        if (blank(order.getId()) || blank(shipment.getOrderId())) throw new DomainException("Order and shipment identifiers are required.");
        if (!Objects.equals(shipment.getOrderId(), order.getId())) throw new DomainException("Shipment does not belong to the specified order.");
        if (order.getStatus() == null) throw new DomainException("Order status is required.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
