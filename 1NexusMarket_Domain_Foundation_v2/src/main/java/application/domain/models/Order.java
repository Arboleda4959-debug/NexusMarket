package application.domain.models;

import application.domain.valueobjects.OrderStatus;
import application.domain.valueobjects.OrderTotal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String id;
    private final String buyerId;
    private final List<OrderItem> items;
    private final OrderTotal total;
    private OrderStatus status;
    private final String paymentId;
    private final String shipmentId;

    public Order(String id, String buyerId, List<OrderItem> items, OrderTotal total,
                 OrderStatus status, String paymentId, String shipmentId) {
        this.id = id;
        this.buyerId = buyerId;
        this.items = items == null ? new ArrayList<>() : new ArrayList<>(items);
        this.total = total;
        this.status = status;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
    }

    public String getId() { return id; }
    public String getBuyerId() { return buyerId; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public OrderTotal getTotal() { return total; }
    public OrderStatus getStatus() { return status; }
    public String getPaymentId() { return paymentId; }
    public String getShipmentId() { return shipmentId; }
}
