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
        this.id = id;
        this.operationId = operationId;
        this.userId = userId;
        this.role = role;
        this.resourceId = resourceId;
        this.timestamp = timestamp;
        this.details = details;
        this.severity = severity;
    }

    public String getId() { return id; }
    public String getOperationId() { return operationId; }
    public String getUserId() { return userId; }
    public UserRole getRole() { return role; }
    public String getResourceId() { return resourceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDetails() { return details; }
    public AuditSeverity getSeverity() { return severity; }
}
