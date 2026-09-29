package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.models.Payment;

public class ValidatePaymentStatusService {

    public void execute(Payment payment) {

        if (payment == null) {
            throw new DomainException("Payment is required.");
        }

        if (payment.getStatus() == null) {
            throw new DomainException("Payment status is required.");
        }
    }
}