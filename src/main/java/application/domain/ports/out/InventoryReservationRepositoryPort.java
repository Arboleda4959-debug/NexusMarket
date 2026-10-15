package application.domain.ports.out;

import application.domain.models.InventoryReservation;
import java.util.List;
import java.util.Optional;

public interface InventoryReservationRepositoryPort {
    Optional<InventoryReservation> findById(String reservationId);
    List<InventoryReservation> findActiveByOrderId(String orderId);
    InventoryReservation save(InventoryReservation reservation);
}
