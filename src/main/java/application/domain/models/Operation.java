package application.domain.models;

import application.domain.valueobjects.OperationType;
import java.time.LocalDateTime;

public class Operation {
    private final String id;
    private final String userId;
    private final OperationType operationType;
    private final String resourceId;
    private final LocalDateTime timestamp;

    public Operation(String id, String userId, OperationType operationType,
                     String resourceId, LocalDateTime timestamp) {
        this.id = require(id, "id");
        this.userId = require(userId, "userId");
        this.operationType = java.util.Objects.requireNonNull(operationType, "operationType is required");
        this.resourceId = require(resourceId, "resourceId");
        this.timestamp = java.util.Objects.requireNonNull(timestamp, "timestamp is required");
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public OperationType getOperationType() { return operationType; }
    public String getResourceId() { return resourceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    private static String require(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " cannot be blank");
        return value.trim();
    }
}
