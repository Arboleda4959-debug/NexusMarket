package application.domain.models;

import application.domain.valueobjects.SellerStatus;
import java.util.Objects;

public class Seller {
    private final String id;
    private final String userId;
    private String name;
    private SellerStatus status;
    public Seller(String id, String userId, String name, SellerStatus status) {
        this.id = require(id, "id"); this.userId = require(userId, "userId");
        this.name = require(name, "name"); this.status = Objects.requireNonNull(status, "Seller status is required");
    }
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public SellerStatus getStatus() { return status; }
    public void changeStatus(SellerStatus status) { this.status = Objects.requireNonNull(status, "Seller status is required"); }
    private static String require(String v, String f) { if (v == null || v.isBlank()) throw new IllegalArgumentException(f + " cannot be blank"); return v.trim(); }
}
