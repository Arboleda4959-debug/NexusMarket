package application.domain.ports.out;

import application.domain.models.Warehouse;
import java.util.Optional;

public interface WarehouseRepositoryPort {
    Optional<Warehouse> findById(String warehouseId);
    Warehouse save(Warehouse warehouse);
}
