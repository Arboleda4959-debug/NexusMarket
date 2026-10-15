package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Product;
import application.domain.valueobjects.ProductStatus;

@SuppressWarnings("deprecation")
public class ValidateProductStatusService {
    public void execute(Product product) {
        if (product == null) throw new DomainException("Product is required.");
        ProductStatus status = product.getStatus();
        if (status != ProductStatus.PUBLISHED && status != ProductStatus.ACTIVE)
            throw new DomainException("Only a published product can be purchased.");
    }
}
