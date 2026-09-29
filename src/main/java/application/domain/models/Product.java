package application.domain.models;

import application.domain.valueobjects.ProductPrice;
import application.domain.valueobjects.ProductStatus;

public class Product {
    private final String id;
    private final String sellerId;
    private String name;
    private String description;
    private ProductPrice price;
    private ProductStatus status;

    public Product(String id, String sellerId, String name, String description,
                   ProductPrice price, ProductStatus status) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public String getId() { return id; }
    public String getSellerId() { return sellerId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ProductPrice getPrice() { return price; }
    public ProductStatus getStatus() { return status; }
}
