package application.domain.models;

import application.domain.valueobjects.WarehouseStatus;
import java.util.Objects;

public class Warehouse {
    private final String id; private final String sellerId; private String name; private WarehouseStatus status;
    public Warehouse(String id, String sellerId, String name, WarehouseStatus status) {
        this.id = require(id, "id"); this.sellerId = require(sellerId, "sellerId");
        this.name = require(name, "name"); this.status = Objects.requireNonNull(status, "Warehouse status is required");
    }
    public String getId() { return id; }
    public String getSellerId() { return sellerId; }
    public String getName() { return name; }
    public WarehouseStatus getStatus() { return status; }
    public void changeStatus(WarehouseStatus status) { this.status = Objects.requireNonNull(status, "Warehouse status is required"); }
    private static String require(String v, String f) { if (v == null || v.isBlank()) throw new IllegalArgumentException(f + " cannot be blank"); return v.trim(); }
}
