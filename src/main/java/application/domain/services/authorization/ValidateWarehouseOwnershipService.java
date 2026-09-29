package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Seller;
import application.domain.models.Warehouse;

public class ValidateWarehouseOwnershipService {

    public void execute(
            Seller seller,
            Warehouse warehouse
    ) {
        if (seller == null) {
            throw new DomainException("Seller is required.");
        }

        if (warehouse == null) {
            throw new DomainException("Warehouse is required.");
        }

        if (!warehouse.getSellerId().equals(seller.getId())) {
            throw new DomainException(
                    "Warehouse does not belong to the specified seller."
            );
        }
    }
}