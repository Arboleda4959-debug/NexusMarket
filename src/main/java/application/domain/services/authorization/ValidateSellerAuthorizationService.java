package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.Seller;
import application.domain.models.User;
import application.domain.valueobjects.SellerStatus;
import application.domain.valueobjects.UserRole;
import java.util.Objects;

public class ValidateSellerAuthorizationService {
    private final ValidateUserAuthorizationService userValidator;
    public ValidateSellerAuthorizationService() { this(new ValidateUserAuthorizationService()); }
    public ValidateSellerAuthorizationService(ValidateUserAuthorizationService validator) { this.userValidator = Objects.requireNonNull(validator); }
    public void execute(User user, Seller seller) {
        userValidator.execute(user);
        if (seller == null) throw new DomainException("Seller is required.");
        if (user.getRole() != UserRole.SELLER) throw new DomainException("SELLER role is required.");
        if (seller.getStatus() != SellerStatus.ACTIVE) throw new DomainException("Seller is not active.");
        if (blank(seller.getUserId()) || blank(seller.getId()) || blank(user.getId())) throw new DomainException("Authorization identifiers are required.");
        if (!Objects.equals(seller.getUserId(), user.getId())) throw new DomainException("User is not authorized to operate on this seller.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
