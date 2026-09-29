package application.domain.services.inventory;

import application.domain.models.Inventory;
import application.domain.valueobjects.InventoryQuantity;
import application.domain.valueobjects.Quantity;

public class ReleaseInventoryReservationService {

    private final ValidateReservedInventoryService
            validateReservedInventoryService;

    public ReleaseInventoryReservationService(
            ValidateReservedInventoryService
                    validateReservedInventoryService
    ) {
        this.validateReservedInventoryService =
                validateReservedInventoryService;
    }

    public void execute(
            Inventory inventory,
            Quantity requestedQuantity
    ) {
        validateReservedInventoryService.execute(
                inventory,
                requestedQuantity
        );

        InventoryQuantity currentQuantity =
                inventory.getQuantity();

        int newAvailable =
                currentQuantity.available()
                        + requestedQuantity.value();

        int newReserved =
                currentQuantity.reserved()
                        - requestedQuantity.value();

        InventoryQuantity updatedQuantity =
                new InventoryQuantity(
                        newAvailable,
                        newReserved
                );

        inventory.updateQuantity(updatedQuantity);
    }
}