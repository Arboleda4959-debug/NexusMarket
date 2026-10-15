package application.domain.models;

import application.domain.valueobjects.InventoryQuantity;
import java.util.Objects;

public class Inventory {
    private final String id; private final String productId; private final String warehouseId; private InventoryQuantity quantity;
    public Inventory(String id, String productId, String warehouseId, InventoryQuantity quantity) {
        this.id = require(id, "id"); this.productId = require(productId, "productId");
        this.warehouseId = require(warehouseId, "warehouseId"); this.quantity = Objects.requireNonNull(quantity, "Inventory quantity is required");
    }
    public String getId() { return id; }
    public String getProductId() { return productId; }
    public String getWarehouseId() { return warehouseId; }
    public InventoryQuantity getQuantity() { return quantity; }
    public void updateQuantity(InventoryQuantity quantity) { this.quantity = Objects.requireNonNull(quantity, "Inventory quantity is required"); }
    private static String require(String v, String f) { if (v == null || v.isBlank()) throw new IllegalArgumentException(f + " cannot be blank"); return v.trim(); }
}
