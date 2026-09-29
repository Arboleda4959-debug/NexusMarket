package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Buyer;
import application.domain.models.Order;
import application.domain.models.User;

public class ValidateBuyerOrderAuthorizationService {

    private final ValidateUserAuthorizationService
            validateUserAuthorizationService;

    public ValidateBuyerOrderAuthorizationService(
            ValidateUserAuthorizationService
                    validateUserAuthorizationService
    ) {
        this.validateUserAuthorizationService =
                validateUserAuthorizationService;
    }

    public void execute(
            User user,
            Buyer buyer,
            Order order
    ) {
        validateUserAuthorizationService.execute(user);

        if (buyer == null) {
            throw new DomainException("Buyer is required.");
        }

        if (order == null) {
            throw new DomainException("Order is required.");
        }

        if (!buyer.getUserId().equals(user.getId())) {
            throw new DomainException(
                    "User is not authorized to operate as this buyer."
            );
        }

        if (!order.getBuyerId().equals(buyer.getId())) {
            throw new DomainException(
                    "Order does not belong to the specified buyer."
            );
        }
    }
}