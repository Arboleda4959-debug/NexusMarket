package application.domain.models;

import application.domain.valueobjects.InventoryQuantity;

public class Inventory {
    private final String id;
    private final String productId;
    private final String warehouseId;
    private InventoryQuantity quantity;

    public Inventory(String id, String productId, String warehouseId, InventoryQuantity quantity) {
        this.id = id;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getProductId() { return productId; }
    public String getWarehouseId() { return warehouseId; }
    public InventoryQuantity getQuantity() { return quantity; }
}
