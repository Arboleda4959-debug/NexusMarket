package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.models.Product;
import application.domain.models.Seller;
import application.domain.models.Warehouse;

public class ValidateSellerInventoryAuthorizationService {

    private final ValidateProductOwnershipService
            validateProductOwnershipService;

    private final ValidateWarehouseOwnershipService
            validateWarehouseOwnershipService;

    public ValidateSellerInventoryAuthorizationService(
            ValidateProductOwnershipService validateProductOwnershipService,
            ValidateWarehouseOwnershipService validateWarehouseOwnershipService
    ) {
        this.validateProductOwnershipService =
                validateProductOwnershipService;

        this.validateWarehouseOwnershipService =
                validateWarehouseOwnershipService;
    }

    public void execute(
            Seller seller,
            Product product,
            Warehouse warehouse,
            Inventory inventory
    ) {
        if (seller == null) {
            throw new DomainException("Seller is required.");
        }

        if (product == null) {
            throw new DomainException("Product is required.");
        }

        if (warehouse == null) {
            throw new DomainException("Warehouse is required.");
        }

        if (inventory == null) {
            throw new DomainException("Inventory is required.");
        }

        validateProductOwnershipService.execute(
                product,
                seller
        );

        validateWarehouseOwnershipService.execute(
                seller,
                warehouse
        );

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