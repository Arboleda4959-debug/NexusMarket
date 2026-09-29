package application.domain.models;

import application.domain.valueobjects.SellerStatus;

public class Seller {
    private final String id;
    private final String userId;
    private String name;
    private SellerStatus status;

    public Seller(String id, String userId, String name, SellerStatus status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public SellerStatus getStatus() { return status; }
}
