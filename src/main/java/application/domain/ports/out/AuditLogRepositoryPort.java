package application.domain.ports.out;

import application.domain.models.AuditLog;

public interface AuditLogRepositoryPort {
    AuditLog append(AuditLog auditLog);
}
