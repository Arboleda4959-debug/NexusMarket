package application.domain.services.inventory;

import application.domain.exceptions.DomainException;
import application.domain.models.Inventory;
import application.domain.valueobjects.InventoryQuantity;
import application.domain.valueobjects.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateInventoryAvailabilityServiceTest {

    private final ValidateInventoryAvailabilityService service =
            new ValidateInventoryAvailabilityService();

    @Test
    void shouldValidateWhenInventoryIsAvailable() {
        Inventory inventory = new Inventory(
                "inv-1",
                "prod-1",
                "warehouse-1",
                new InventoryQuantity(10, 0)
        );

        Quantity requested = new Quantity(5);

        assertDoesNotThrow(() -> service.execute(inventory, requested));
    }

    @Test
    void shouldRejectWhenInventoryIsInsufficient() {
        Inventory inventory = new Inventory(
                "inv-1",
                "prod-1",
                "warehouse-1",
                new InventoryQuantity(3, 0)
        );

        Quantity requested = new Quantity(5);

        assertThrows(
                DomainException.class,
                () -> service.execute(inventory, requested)
        );
    }

    @Test
    void shouldRejectNullInventory() {
        Quantity requested = new Quantity(5);

        assertThrows(
                DomainException.class,
                () -> service.execute(null, requested)
        );
    }

    @Test
    void shouldRejectNullQuantity() {
        Inventory inventory = new Inventory(
                "inv-1",
                "prod-1",
                "warehouse-1",
                new InventoryQuantity(10, 0)
        );

        assertThrows(
                DomainException.class,
                () -> service.execute(inventory, null)
        );
    }
}