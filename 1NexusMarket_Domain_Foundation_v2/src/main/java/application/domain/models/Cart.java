package application.domain.models;

import application.domain.valueobjects.CartStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final String id;
    private final String buyerId;
    private final List<CartItem> items;
    private CartStatus status;

    public Cart(String id, String buyerId, List<CartItem> items, CartStatus status) {
        this.id = id;
        this.buyerId = buyerId;
        this.items = items == null ? new ArrayList<>() : new ArrayList<>(items);
        this.status = status;
    }

    public String getId() { return id; }
    public String getBuyerId() { return buyerId; }
    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }
    public CartStatus getStatus() { return status; }
}
