package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Product;
import application.domain.models.User;
import application.domain.valueobjects.UserRole;

public class ValidateSellerResourceAuthorizationService {

    private final ValidateUserAuthorizationService
            validateUserAuthorizationService;

    public ValidateSellerResourceAuthorizationService(
            ValidateUserAuthorizationService validateUserAuthorizationService) {
        this.validateUserAuthorizationService =
                validateUserAuthorizationService;
    }

    public void execute(User user, Product product) {

        validateUserAuthorizationService.execute(user);

        if (product == null) {
            throw new DomainException("Product is required.");
        }

        if (!UserRole.SELLER.equals(user.getRole())) {
            throw new DomainException(
                    "SELLER role is required for this operation."
            );
        }

        if (!user.getId().equals(product.getSellerId())) {
            throw new DomainException(
                    "The seller cannot operate on another seller's product."
            );
        }
    }
}