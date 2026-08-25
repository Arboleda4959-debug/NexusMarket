package application.domain.models;

import application.domain.valueobjects.Email;
import application.domain.valueobjects.UserRole;
import application.domain.valueobjects.UserStatus;

public class User {
    private final String id;
    private String name;
    private Email email;
    private UserStatus status;
    private UserRole role;

    public User(String id, String name, Email email, UserStatus status, UserRole role) {
        this.id = require(id, "id");
        this.name = require(name, "name");
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Email getEmail() { return email; }
    public UserStatus getStatus() { return status; }
    public UserRole getRole() { return role; }

    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value;
    }
}
