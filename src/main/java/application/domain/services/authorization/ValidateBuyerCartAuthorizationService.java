package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Buyer;
import application.domain.models.Cart;
import application.domain.models.User;

public class ValidateBuyerCartAuthorizationService {

    private final ValidateUserAuthorizationService
            validateUserAuthorizationService;

    public ValidateBuyerCartAuthorizationService(
            ValidateUserAuthorizationService
                    validateUserAuthorizationService
    ) {
        this.validateUserAuthorizationService =
                validateUserAuthorizationService;
    }

    public void execute(
            User user,
            Buyer buyer,
            Cart cart
    ) {
        validateUserAuthorizationService.execute(user);

        if (buyer == null) {
            throw new DomainException("Buyer is required.");
        }

        if (cart == null) {
            throw new DomainException("Cart is required.");
        }

        if (!buyer.getUserId().equals(user.getId())) {
            throw new DomainException(
                    "User is not authorized to operate as this buyer."
            );
        }

        if (!cart.getBuyerId().equals(buyer.getId())) {
            throw new DomainException(
                    "Cart does not belong to the specified buyer."
            );
        }
    }
}