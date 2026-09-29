package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Product;
import application.domain.models.Seller;

public class ValidateProductOwnershipService {

    public void execute(Product product, Seller seller) {

        if (product == null) {
            throw new DomainException("Product is required.");
        }

        if (seller == null) {
            throw new DomainException("Seller is required.");
        }

        if (!product.getSellerId().equals(seller.getId())) {
            throw new DomainException(
                    "Product does not belong to the specified seller."
            );
        }
    }
}