package application.domain.models;

import application.domain.valueobjects.Address;
import application.domain.valueobjects.ShipmentStatus;
import application.domain.valueobjects.TrackingCode;

public class Shipment {
    private final String id;
    private final String orderId;
    private ShipmentStatus status;
    private final TrackingCode trackingCode;
    private final Address deliveryInformation;

    public Shipment(String id, String orderId, ShipmentStatus status,
                    TrackingCode trackingCode, Address deliveryInformation) {
        this.id = require(id, "id");
        this.orderId = require(orderId, "orderId");
        this.status = java.util.Objects.requireNonNull(status, "status is required");
        this.trackingCode = java.util.Objects.requireNonNull(trackingCode, "trackingCode is required");
        this.deliveryInformation = java.util.Objects.requireNonNull(deliveryInformation, "deliveryInformation is required");
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public ShipmentStatus getStatus() { return status; }
    public TrackingCode getTrackingCode() { return trackingCode; }
    public Address getDeliveryInformation() { return deliveryInformation; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
