package application.domain.services.shipment;

import application.domain.exceptions.DomainException;
import application.domain.models.Shipment;

public class ValidateShipmentStatusService {

    public void execute(Shipment shipment) {

        if (shipment == null) {
            throw new DomainException("Shipment is required.");
        }

        if (shipment.getStatus() == null) {
            throw new DomainException("Shipment status is required.");
        }
    }
}