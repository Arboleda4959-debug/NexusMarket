package application.domain.valueobjects;

import java.util.Objects;

public record ContactInformation(String name, String phone, Email email) {
    public ContactInformation {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Contact name cannot be blank");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Contact phone cannot be blank");
        }
        Objects.requireNonNull(email, "Contact email cannot be null");
        name = name.trim();
        phone = phone.trim();
    }
}
