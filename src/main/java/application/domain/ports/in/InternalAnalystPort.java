package application.domain.ports.in;

import application.domain.models.AuditLog;

public interface InternalAnalystPort {
    void recordAuditEntry(AuditLog auditLog);
}
