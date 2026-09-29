package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Cart;
import application.domain.models.User;
import application.domain.valueobjects.UserRole;

public class ValidateBuyerResourceAuthorizationService {

    private final ValidateUserAuthorizationService
            validateUserAuthorizationService;

    public ValidateBuyerResourceAuthorizationService(
            ValidateUserAuthorizationService validateUserAuthorizationService) {
        this.validateUserAuthorizationService =
                validateUserAuthorizationService;
    }

    public void execute(User user, Cart cart) {

        validateUserAuthorizationService.execute(user);

        if (cart == null) {
            throw new DomainException("Cart is required.");
        }

        if (!UserRole.BUYER.equals(user.getRole())) {
            throw new DomainException(
                    "BUYER role is required for this operation."
            );
        }

        if (!user.getId().equals(cart.getBuyerId())) {
            throw new DomainException(
                    "The buyer cannot operate on another buyer's cart."
            );
        }
    }
}