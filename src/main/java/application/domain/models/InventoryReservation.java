package application.domain.models;

import application.domain.exceptions.DomainException;
import application.domain.valueobjects.Quantity;
import application.domain.valueobjects.ReservationStatus;
import java.util.Objects;

/** Identifiable reservation prevents a second release of the same reservation. */
public class InventoryReservation {
    private final String id;
    private final String inventoryId;
    private final String orderId;
    private final Quantity quantity;
    private ReservationStatus status;

    public InventoryReservation(String id, String inventoryId, String orderId, Quantity quantity, ReservationStatus status) {
        this.id = require(id, "id"); this.inventoryId = require(inventoryId, "inventoryId"); this.orderId = require(orderId, "orderId");
        this.quantity = Objects.requireNonNull(quantity, "Reservation quantity is required");
        if (quantity.value() <= 0) throw new IllegalArgumentException("Reservation quantity must be greater than zero");
        this.status = Objects.requireNonNull(status, "Reservation status is required");
    }
    public String getId() { return id; }
    public String getInventoryId() { return inventoryId; }
    public String getOrderId() { return orderId; }
    public Quantity getQuantity() { return quantity; }
    public ReservationStatus getStatus() { return status; }
    public void markReleased() {
        if (status != ReservationStatus.ACTIVE) throw new DomainException("Only an active reservation can be released.");
        status = ReservationStatus.RELEASED;
    }
    public void markConsumed() {
        if (status != ReservationStatus.ACTIVE) throw new DomainException("Only an active reservation can be consumed.");
        status = ReservationStatus.CONSUMED;
    }
    private static String require(String value, String field) { if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank"); return value.trim(); }
}
