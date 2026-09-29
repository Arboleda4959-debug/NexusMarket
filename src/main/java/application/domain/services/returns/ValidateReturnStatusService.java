package application.domain.services.returns;

import application.domain.exceptions.DomainException;
import application.domain.models.Return;

public class ValidateReturnStatusService {

    public void execute(Return returnRequest) {

        if (returnRequest == null) {
            throw new DomainException("Return is required.");
        }

        if (returnRequest.getStatus() == null) {
            throw new DomainException("Return status is required.");
        }
    }
}