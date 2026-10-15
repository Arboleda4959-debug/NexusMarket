package application.domain.ports.out;

import application.domain.models.Inventory;
import java.util.Optional;

/** Implement save/update atomically in the persistence adapter to prevent overselling. */
public interface InventoryRepositoryPort {
    Optional<Inventory> findByProductIdAndWarehouseId(String productId, String warehouseId);
    Inventory save(Inventory inventory);
}
