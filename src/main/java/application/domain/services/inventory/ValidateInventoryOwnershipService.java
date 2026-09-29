package application.domain.services.inventory;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.models.Product;
import application.domain.models.Warehouse;

public class ValidateInventoryOwnershipService {

    public void execute(
            Product product,
            Warehouse warehouse,
            Inventory inventory
    ) {
        if (product == null) {
            throw new DomainException("Product is required.");
        }

        if (warehouse == null) {
            throw new DomainException("Warehouse is required.");
        }

        if (inventory == null) {
            throw new DomainException("Inventory is required.");
        }

        if (!inventory.getProductId().equals(product.getId())) {
            throw new DomainException(
                    "Inventory does not belong to the specified product."
            );
        }

        if (!inventory.getWarehouseId().equals(warehouse.getId())) {
            throw new DomainException(
                    "Inventory does not belong to the specified warehouse."
            );
        }
    }
}