package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Buyer;
import application.domain.models.Cart;
import application.domain.models.User;
import application.domain.valueobjects.UserRole;
import application.domain.valueobjects.BuyerStatus;
import java.util.Objects;

public class ValidateBuyerCartAuthorizationService {
    private final ValidateUserAuthorizationService validateUserAuthorizationService;
    public ValidateBuyerCartAuthorizationService(ValidateUserAuthorizationService validator) {
        this.validateUserAuthorizationService = Objects.requireNonNull(validator);
    }
    public void execute(User user, Buyer buyer, Cart cart) {
        validateUserAuthorizationService.execute(user);
        if (buyer == null) throw new DomainException("Buyer is required.");
        if (cart == null) throw new DomainException("Cart is required.");
        if (user.getRole() != UserRole.BUYER) throw new DomainException("BUYER role is required.");
        if (buyer.getStatus() != BuyerStatus.ACTIVE) throw new DomainException("Buyer is not active.");
        if (blank(user.getId()) || blank(buyer.getUserId()) || blank(buyer.getId()) || blank(cart.getBuyerId()))
            throw new DomainException("Authorization identifiers are required.");
        if (!Objects.equals(buyer.getUserId(), user.getId())) throw new DomainException("User is not authorized to operate as this buyer.");
        if (!Objects.equals(cart.getBuyerId(), buyer.getId())) throw new DomainException("Cart does not belong to the specified buyer.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
