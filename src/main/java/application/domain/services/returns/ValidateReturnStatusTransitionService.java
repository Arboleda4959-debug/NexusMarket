package application.domain.services.returns;

import application.domain.exceptions.DomainException;
import application.domain.valueobjects.ReturnStatus;

public class ValidateReturnStatusTransitionService {
    public void execute(ReturnStatus current, ReturnStatus next) {
        if (current == null || next == null) throw new DomainException("Return statuses must be provided.");
        boolean valid = switch (current) {
            case REQUESTED -> next == ReturnStatus.APPROVED || next == ReturnStatus.REJECTED;
            case APPROVED -> next == ReturnStatus.RECEIVED;
            case RECEIVED -> next == ReturnStatus.COMPLETED;
            case REJECTED, COMPLETED -> false;
        };
        if (!valid) throw new DomainException("Invalid return status transition from " + current + " to " + next + ".");
    }
}
