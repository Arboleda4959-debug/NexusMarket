package application.domain.services.inventory;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.models.Product;
import application.domain.models.Warehouse;
import java.util.Objects;

public class ValidateInventoryOwnershipService {
    public void execute(Product product, Warehouse warehouse, Inventory inventory) {
        if (product == null) throw new DomainException("Product is required.");
        if (warehouse == null) throw new DomainException("Warehouse is required.");
        if (inventory == null) throw new DomainException("Inventory is required.");
        if (blank(product.getId()) || blank(warehouse.getId()) || blank(inventory.getProductId()) || blank(inventory.getWarehouseId()))
            throw new DomainException("Product, warehouse and inventory identifiers are required.");
        if (!Objects.equals(inventory.getProductId(), product.getId())) throw new DomainException("Inventory does not belong to the specified product.");
        if (!Objects.equals(inventory.getWarehouseId(), warehouse.getId())) throw new DomainException("Inventory does not belong to the specified warehouse.");
        if (inventory.getQuantity() == null) throw new DomainException("Inventory quantity is required.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
