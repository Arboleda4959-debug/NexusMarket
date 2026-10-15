package application.domain.services.inventory;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.models.InventoryReservation;
import application.domain.valueobjects.InventoryQuantity;
import application.domain.valueobjects.Quantity;
import application.domain.valueobjects.ReservationStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReservationTest {
    @Test void reservationCanBeReleasedOnlyOnce() {
        Inventory inventory = new Inventory("inv-1", "prod-1", "wh-1", new InventoryQuantity(5, 3));
        InventoryReservation reservation = new InventoryReservation("res-1", "inv-1", "order-1", new Quantity(2), ReservationStatus.ACTIVE);
        var service = new ReleaseInventoryReservationService(new ValidateReservedInventoryService());
        service.execute(inventory, reservation);
        assertEquals(new InventoryQuantity(7, 1), inventory.getQuantity());
        assertEquals(ReservationStatus.RELEASED, reservation.getStatus());
        assertThrows(DomainException.class, () -> service.execute(inventory, reservation));
    }
    @Test void reservationMustBelongToInventory() {
        Inventory inventory = new Inventory("inv-1", "prod-1", "wh-1", new InventoryQuantity(5, 3));
        InventoryReservation reservation = new InventoryReservation("res-1", "inv-2", "order-1", new Quantity(2), ReservationStatus.ACTIVE);
        var service = new ReleaseInventoryReservationService(new ValidateReservedInventoryService());
        assertThrows(DomainException.class, () -> service.execute(inventory, reservation));
    }
}
