package application.domain.services.refunds;

import application.domain.exceptions.DomainException;
import application.domain.valueobjects.RefundStatus;

public class ValidateRefundStatusTransitionService {
    public void execute(RefundStatus current, RefundStatus next) {
        if (current == null || next == null) throw new DomainException("Refund statuses must be provided.");
        boolean valid = current == RefundStatus.PENDING && (next == RefundStatus.PROCESSED || next == RefundStatus.REJECTED);
        if (!valid) throw new DomainException("Invalid refund status transition from " + current + " to " + next + ".");
    }
}
