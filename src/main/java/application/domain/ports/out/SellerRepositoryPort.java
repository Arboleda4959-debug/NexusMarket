package application.domain.ports.out;

import application.domain.models.Seller;
import java.util.Optional;

public interface SellerRepositoryPort {
    Optional<Seller> findById(String sellerId);
    Optional<Seller> findByUserId(String userId);
    Seller save(Seller seller);
}
