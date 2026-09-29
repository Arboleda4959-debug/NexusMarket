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
        this.id = id;
        this.userId = userId;
        this.operationType = operationType;
        this.resourceId = resourceId;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public OperationType getOperationType() { return operationType; }
    public String getResourceId() { return resourceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
