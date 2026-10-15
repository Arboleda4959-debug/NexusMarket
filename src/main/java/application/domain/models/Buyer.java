package application.domain.models;

import application.domain.valueobjects.BuyerStatus;
import application.domain.valueobjects.ContactInformation;
import application.domain.valueobjects.UserStatus;
import java.util.Objects;

public class Buyer {
    private final String id;
    private final String userId;
    private BuyerStatus status;
    private ContactInformation contactInformation;

    public Buyer(String id, String userId, BuyerStatus status, ContactInformation contactInformation) {
        this.id = require(id, "id");
        this.userId = require(userId, "userId");
        this.status = Objects.requireNonNull(status, "Buyer status is required");
        this.contactInformation = Objects.requireNonNull(contactInformation, "Contact information is required");
    }

    /** Compatibility constructor for the earlier foundation; new code should use BuyerStatus. */
    @Deprecated
    public Buyer(String id, String userId, UserStatus status, ContactInformation contactInformation) {
        this(id, userId, status == null ? null : BuyerStatus.valueOf(status.name()), contactInformation);
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public BuyerStatus getStatus() { return status; }
    public ContactInformation getContactInformation() { return contactInformation; }
    public void changeStatus(BuyerStatus newStatus) { this.status = Objects.requireNonNull(newStatus, "Buyer status is required"); }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
