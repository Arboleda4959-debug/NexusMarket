package application.domain.models;

import application.domain.valueobjects.WarehouseStatus;

public class Warehouse {
    private final String id;
    private final String sellerId;
    private String name;
    private WarehouseStatus status;

    public Warehouse(String id, String sellerId, String name, WarehouseStatus status) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.status = status;
    }

    public String getId() { return id; }
    public String getSellerId() { return sellerId; }
    public String getName() { return name; }
    public WarehouseStatus getStatus() { return status; }
}
