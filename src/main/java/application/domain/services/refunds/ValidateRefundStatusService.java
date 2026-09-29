package application.domain.services.refunds;

import application.domain.exceptions.DomainException;
import application.domain.models.Refund;

public class ValidateRefundStatusService {

    public void execute(Refund refund) {

        if (refund == null) {
            throw new DomainException("Refund is required.");
        }

        if (refund.getStatus() == null) {
            throw new DomainException("Refund status is required.");
        }
    }
}