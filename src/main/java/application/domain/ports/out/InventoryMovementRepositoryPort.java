package application.domain.ports.out;

import application.domain.models.InventoryMovement;

public interface InventoryMovementRepositoryPort {
    InventoryMovement append(InventoryMovement movement);
}
