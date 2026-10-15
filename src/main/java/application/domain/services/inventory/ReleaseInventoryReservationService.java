package application.domain.services.inventory;

import application.domain.models.Inventory;
import application.domain.models.InventoryReservation;
import application.domain.exceptions.DomainException;
import application.domain.valueobjects.ReservationStatus;
import java.util.Objects;
import application.domain.valueobjects.InventoryQuantity;
import application.domain.valueobjects.Quantity;

public class ReleaseInventoryReservationService {

    private final ValidateReservedInventoryService
            validateReservedInventoryService;

    public ReleaseInventoryReservationService(
            ValidateReservedInventoryService
                    validateReservedInventoryService
    ) {
        this.validateReservedInventoryService =
                validateReservedInventoryService;
    }

    /**
     * Preferred operation: uses a reservation identity so the same reservation
     * cannot be released twice. Persist inventory and reservation atomically.
     */
    public void execute(Inventory inventory, InventoryReservation reservation) {
        if (inventory == null) throw new DomainException("Inventory is required.");
        if (reservation == null) throw new DomainException("Inventory reservation is required.");
        if (!Objects.equals(inventory.getId(), reservation.getInventoryId()))
            throw new DomainException("Reservation does not belong to the specified inventory.");
        if (reservation.getStatus() != ReservationStatus.ACTIVE)
            throw new DomainException("Only an active reservation can be released.");
        execute(inventory, reservation.getQuantity());
        reservation.markReleased();
    }

    /** @deprecated Prefer the reservation-aware overload to prevent duplicate release. */
    @Deprecated
    public void execute(
            Inventory inventory,
            Quantity requestedQuantity
    ) {
        validateReservedInventoryService.execute(
                inventory,
                requestedQuantity
        );

        InventoryQuantity currentQuantity =
                inventory.getQuantity();

        int newAvailable =
                currentQuantity.available()
                        + requestedQuantity.value();

        int newReserved =
                currentQuantity.reserved()
                        - requestedQuantity.value();

        InventoryQuantity updatedQuantity =
                new InventoryQuantity(
                        newAvailable,
                        newReserved
                );

        inventory.updateQuantity(updatedQuantity);
    }
}