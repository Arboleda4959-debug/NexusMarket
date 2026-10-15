package application.domain.ports.out;

import application.domain.models.Product;
import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> findById(String productId);
    Product save(Product product);
}
