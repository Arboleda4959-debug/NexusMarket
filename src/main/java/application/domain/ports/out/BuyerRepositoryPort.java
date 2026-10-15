package application.domain.ports.out;

import application.domain.models.Buyer;
import java.util.Optional;

public interface BuyerRepositoryPort {
    Optional<Buyer> findById(String buyerId);
    Optional<Buyer> findByUserId(String userId);
    Buyer save(Buyer buyer);
}
