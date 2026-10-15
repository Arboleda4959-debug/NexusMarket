package application.domain.services.cart;

import application.domain.exceptions.DomainException;
import application.domain.models.Product;
import application.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ValidateProductStatusServiceTest {
    private Product product(ProductStatus status) { return new Product("p-1", "s-1", "Product", "desc", new ProductPrice(new BigDecimal("10"), "COP"), status, ProductType.PHYSICAL); }
    @Test void publishedProductCanBePurchased() { assertDoesNotThrow(() -> new ValidateProductStatusService().execute(product(ProductStatus.PUBLISHED))); }
    @Test void suspendedProductCannotBePurchased() { assertThrows(DomainException.class, () -> new ValidateProductStatusService().execute(product(ProductStatus.SUSPENDED))); }
}
