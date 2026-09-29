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
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.trackingCode = trackingCode;
        this.deliveryInformation = deliveryInformation;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public ShipmentStatus getStatus() { return status; }
    public TrackingCode getTrackingCode() { return trackingCode; }
    public Address getDeliveryInformation() { return deliveryInformation; }
}
