package application.domain.services.refunds;

import application.domain.exceptions.DomainException;
import application.domain.models.Refund;
import application.domain.models.Return;

public class ValidateRefundReturnService {

    public void execute(
            Return returnRequest,
            Refund refund
    ) {
        if (returnRequest == null) {
            throw new DomainException("Return is required.");
        }

        if (refund == null) {
            throw new DomainException("Refund is required.");
        }

        if (!refund.getReturnId().equals(returnRequest.getId())) {
            throw new DomainException(
                    "Refund does not belong to the specified return."
            );
        }
    }
}