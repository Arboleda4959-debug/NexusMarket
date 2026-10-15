package application.domain.models;

import application.domain.valueobjects.AuditSeverity;
import application.domain.valueobjects.UserRole;
import java.time.LocalDateTime;

public class AuditLog {
    private final String id;
    private final String operationId;
    private final String userId;
    private final UserRole role;
    private final String resourceId;
    private final LocalDateTime timestamp;
    private final String details;
    private final AuditSeverity severity;

    public AuditLog(String id, String operationId, String userId, UserRole role,
                    String resourceId, LocalDateTime timestamp, String details,
                    AuditSeverity severity) {
        this.id = require(id, "id");
        this.operationId = require(operationId, "operationId");
        this.userId = require(userId, "userId");
        this.role = java.util.Objects.requireNonNull(role, "role is required");
        this.resourceId = require(resourceId, "resourceId");
        this.timestamp = java.util.Objects.requireNonNull(timestamp, "timestamp is required");
        this.details = details == null ? "" : details.trim();
        this.severity = java.util.Objects.requireNonNull(severity, "severity is required");
    }

    public String getId() { return id; }
    public String getOperationId() { return operationId; }
    public String getUserId() { return userId; }
    public UserRole getRole() { return role; }
    public String getResourceId() { return resourceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDetails() { return details; }
    public AuditSeverity getSeverity() { return severity; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
