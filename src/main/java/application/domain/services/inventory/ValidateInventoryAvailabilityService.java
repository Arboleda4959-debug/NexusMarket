package application.domain.services.inventory;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.valueobjects.Quantity;

public class ValidateInventoryAvailabilityService {

    public void execute(
            Inventory inventory,
            Quantity requestedQuantity
    ) {

        if (inventory == null) {
            throw new DomainException(
                    "Inventory is required."
            );
        }

        if (requestedQuantity == null) {
            throw new DomainException(
                    "Requested quantity is required."
            );
        }

        if (inventory.getQuantity().available()
                < requestedQuantity.value()) {

            throw new DomainException(
                    "Insufficient inventory availability."
            );
        }
    }
}