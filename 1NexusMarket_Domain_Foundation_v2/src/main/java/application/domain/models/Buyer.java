package application.domain.models;

import application.domain.valueobjects.ContactInformation;
import application.domain.valueobjects.UserStatus;

public class Buyer {
    private final String id;
    private final String userId;
    private UserStatus status;
    private ContactInformation contactInformation;

    public Buyer(String id, String userId, UserStatus status, ContactInformation contactInformation) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.contactInformation = contactInformation;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public UserStatus getStatus() { return status; }
    public ContactInformation getContactInformation() { return contactInformation; }
}
