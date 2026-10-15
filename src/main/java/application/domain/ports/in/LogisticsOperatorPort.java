package application.domain.ports.in;

import application.domain.models.Order;
import application.domain.models.Shipment;
import application.domain.valueobjects.ShipmentStatus;

public interface LogisticsOperatorPort {
    void validateShipmentForOrder(Order order, Shipment shipment);
    void validateShipmentStatusTransition(ShipmentStatus current, ShipmentStatus next);
}
