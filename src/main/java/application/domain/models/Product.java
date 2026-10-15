package application.domain.models;

import application.domain.valueobjects.ProductPrice;
import application.domain.valueobjects.ProductStatus;
import application.domain.valueobjects.ProductType;
import java.util.Objects;

public class Product {
    private final String id;
    private final String sellerId;
    private final ProductType type;
    private String name;
    private String description;
    private ProductPrice price;
    private ProductStatus status;

    public Product(String id, String sellerId, String name, String description,
                   ProductPrice price, ProductStatus status, ProductType type) {
        this.id = require(id, "id");
        this.sellerId = require(sellerId, "sellerId");
        this.name = require(name, "name");
        this.description = description == null ? "" : description.trim();
        this.price = Objects.requireNonNull(price, "Product price is required");
        this.status = Objects.requireNonNull(status, "Product status is required");
        this.type = Objects.requireNonNull(type, "Product type is required");
    }

    /** Compatibility constructor for existing callers; new code should specify ProductType explicitly. */
    @Deprecated
    public Product(String id, String sellerId, String name, String description,
                   ProductPrice price, ProductStatus status) {
        this(id, sellerId, name, description, price, status, ProductType.PHYSICAL);
    }

    public String getId() { return id; }
    public String getSellerId() { return sellerId; }
    public ProductType getType() { return type; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ProductPrice getPrice() { return price; }
    public ProductStatus getStatus() { return status; }
    public void updatePrice(ProductPrice price) { this.price = Objects.requireNonNull(price, "Product price is required"); }
    public void changeStatus(ProductStatus status) { this.status = Objects.requireNonNull(status, "Product status is required"); }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
