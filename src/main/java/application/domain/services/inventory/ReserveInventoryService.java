package application.domain.services.inventory;

import application.domain.models.Inventory;
import application.domain.valueobjects.InventoryQuantity;
import application.domain.valueobjects.Quantity;

public class ReserveInventoryService {

    private final ValidateInventoryAvailabilityService
            validateInventoryAvailabilityService;

    public ReserveInventoryService(
            ValidateInventoryAvailabilityService
                    validateInventoryAvailabilityService
    ) {
        this.validateInventoryAvailabilityService =
                validateInventoryAvailabilityService;
    }

    public void execute(
            Inventory inventory,
            Quantity requestedQuantity
    ) {
        validateInventoryAvailabilityService.execute(
                inventory,
                requestedQuantity
        );

        InventoryQuantity currentQuantity =
                inventory.getQuantity();

        int newAvailable =
                currentQuantity.available()
                        - requestedQuantity.value();

        int newReserved =
                currentQuantity.reserved()
                        + requestedQuantity.value();

        InventoryQuantity updatedQuantity =
                new InventoryQuantity(
                        newAvailable,
                        newReserved
                );

        inventory.updateQuantity(updatedQuantity);
    }
}