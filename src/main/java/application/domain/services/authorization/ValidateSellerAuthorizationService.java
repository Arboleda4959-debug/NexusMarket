package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Seller;
import application.domain.models.User;

public class ValidateSellerAuthorizationService {

    public void execute(User user, Seller seller) {

        if (user == null) {
            throw new DomainException("User is required.");
        }

        if (seller == null) {
            throw new DomainException("Seller is required.");
        }

        if (!seller.getUserId().equals(user.getId())) {
            throw new DomainException(
                    "User is not authorized to operate on this seller."
            );
        }
    }
}