package application.domain.services.shipment;

import application.domain.exceptions.DomainException;
import application.domain.valueobjects.ShipmentStatus;

public class ValidateShipmentStatusTransitionService {
    public void execute(ShipmentStatus current, ShipmentStatus next) {
        if (current == null || next == null) throw new DomainException("Shipment statuses must be provided.");
        boolean valid = switch (current) {
            case PENDING -> next == ShipmentStatus.PREPARING || next == ShipmentStatus.CANCELLED;
            case PREPARING -> next == ShipmentStatus.SHIPPED || next == ShipmentStatus.CANCELLED;
            case SHIPPED -> next == ShipmentStatus.IN_TRANSIT || next == ShipmentStatus.DELIVERED;
            case IN_TRANSIT -> next == ShipmentStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
        if (!valid) throw new DomainException("Invalid shipment status transition from " + current + " to " + next + ".");
    }
}
