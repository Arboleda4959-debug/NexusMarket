package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Buyer;
import application.domain.models.Cart;
import application.domain.models.User;
import java.util.Objects;

/** Prefer execute(User, Buyer, Cart), which can validate the complete ownership chain. */
public class ValidateBuyerResourceAuthorizationService {
    private final ValidateUserAuthorizationService validator;
    private final ValidateBuyerCartAuthorizationService buyerCartValidator;
    public ValidateBuyerResourceAuthorizationService(ValidateUserAuthorizationService validator) {
        this.validator = Objects.requireNonNull(validator);
        this.buyerCartValidator = new ValidateBuyerCartAuthorizationService(validator);
    }
    public void execute(User user, Buyer buyer, Cart cart) {
        buyerCartValidator.execute(user, buyer, cart);
    }
    /** Fail closed: without Buyer, the service cannot prove User -> Buyer -> Cart ownership. */
    @Deprecated
    public void execute(User user, Cart cart) {
        validator.execute(user);
        if (cart == null) throw new DomainException("Cart is required.");
        throw new DomainException("Buyer profile is required to validate cart ownership securely.");
    }
}
