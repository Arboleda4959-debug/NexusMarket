package application.domain.models;

import application.domain.valueobjects.MovementType;
import application.domain.valueobjects.Quantity;
import java.time.LocalDateTime;
import java.util.Objects;

/** Immutable record of an inventory change; persistence should append, not overwrite it. */
public class InventoryMovement {
    private final String id;
    private final String inventoryId;
    private final MovementType type;
    private final Quantity quantity;
    private final String referenceId;
    private final LocalDateTime occurredAt;

    public InventoryMovement(String id, String inventoryId, MovementType type, Quantity quantity,
                             String referenceId, LocalDateTime occurredAt) {
        this.id = require(id, "id");
        this.inventoryId = require(inventoryId, "inventoryId");
        this.type = Objects.requireNonNull(type, "Movement type is required");
        this.quantity = Objects.requireNonNull(quantity, "Movement quantity is required");
        if (quantity.value() <= 0) throw new IllegalArgumentException("Movement quantity must be greater than zero");
        this.referenceId = referenceId == null ? "" : referenceId.trim();
        this.occurredAt = Objects.requireNonNull(occurredAt, "Movement timestamp is required");
    }
    public String getId() { return id; }
    public String getInventoryId() { return inventoryId; }
    public MovementType getType() { return type; }
    public Quantity getQuantity() { return quantity; }
    public String getReferenceId() { return referenceId; }
    public LocalDateTime getOccurredAt() { return occurredAt; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
