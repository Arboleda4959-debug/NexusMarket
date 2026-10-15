package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.valueobjects.PaymentStatus;

public class ValidatePaymentStatusTransitionService {
    public void execute(PaymentStatus current, PaymentStatus next) {
        if (current == null || next == null) throw new DomainException("Payment statuses must be provided.");
        boolean valid = switch (current) {
            case PENDING -> next == PaymentStatus.APPROVED || next == PaymentStatus.REJECTED;
            case APPROVED -> next == PaymentStatus.REFUNDED;
            case REJECTED, REFUNDED -> false;
        };
        if (!valid) throw new DomainException("Invalid payment status transition from " + current + " to " + next + ".");
    }
}
