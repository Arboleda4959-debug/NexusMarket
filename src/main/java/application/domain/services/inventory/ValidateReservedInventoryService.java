package application.domain.services.inventory;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.valueobjects.InventoryQuantity;
import application.domain.valueobjects.Quantity;

public class ValidateReservedInventoryService {
    public void execute(Inventory inventory, Quantity requestedQuantity) {
        if (inventory == null) throw new DomainException("Inventory is required.");
        if (requestedQuantity == null) throw new DomainException("Requested quantity is required.");
        if (requestedQuantity.value() <= 0) throw new DomainException("Requested quantity must be greater than zero.");
        InventoryQuantity current = inventory.getQuantity();
        if (current == null) throw new DomainException("Inventory quantity is required.");
        if (current.reserved() < requestedQuantity.value()) throw new DomainException("Insufficient reserved inventory to release.");
    }
}
