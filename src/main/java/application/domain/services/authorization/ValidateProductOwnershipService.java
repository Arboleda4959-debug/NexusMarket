package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Product;
import application.domain.models.Seller;
import java.util.Objects;

public class ValidateProductOwnershipService {
    public void execute(Product product, Seller seller) {
        if (product == null) throw new DomainException("Product is required.");
        if (seller == null) throw new DomainException("Seller is required.");
        if (blank(product.getId()) || blank(product.getSellerId()) || blank(seller.getId()))
            throw new DomainException("Product and seller identifiers are required.");
        if (!Objects.equals(product.getSellerId(), seller.getId()))
            throw new DomainException("Product does not belong to the specified seller.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
