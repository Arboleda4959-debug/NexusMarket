package application.domain.valueobjects;

public record InventoryQuantity(int available, int reserved) {
    public InventoryQuantity {
        if (available < 0 || reserved < 0) {
            throw new IllegalArgumentException("Inventory quantities cannot be negative");
        }
    }
}
