package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.*;
import application.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthorizationServicesTest {
    @Test void buyerMustMatchUserAndCart() {
        var service = new ValidateBuyerCartAuthorizationService(new ValidateUserAuthorizationService());
        User user = new User("user-1", "Buyer", new Email("buyer@example.com"), UserStatus.ACTIVE, UserRole.BUYER);
        Buyer buyer = new Buyer("buyer-1", "user-1", BuyerStatus.ACTIVE, new ContactInformation("Buyer", "3000000000", new Email("buyer@example.com")));
        Cart cart = new Cart("cart-1", "buyer-1", java.util.List.of(), CartStatus.ACTIVE);
        assertDoesNotThrow(() -> service.execute(user, buyer, cart));
        Cart other = new Cart("cart-2", "buyer-2", java.util.List.of(), CartStatus.ACTIVE);
        assertThrows(DomainException.class, () -> service.execute(user, buyer, other));
    }
    @Test void sellerMustBeLinkedToUserAndActive() {
        var service = new ValidateSellerAuthorizationService();
        User user = new User("user-1", "Seller", new Email("seller@example.com"), UserStatus.ACTIVE, UserRole.SELLER);
        Seller seller = new Seller("seller-1", "user-1", "Seller One", SellerStatus.ACTIVE);
        assertDoesNotThrow(() -> service.execute(user, seller));
        Seller other = new Seller("seller-2", "user-2", "Seller Two", SellerStatus.ACTIVE);
        assertThrows(DomainException.class, () -> service.execute(user, other));
    }
    @Test void legacyAuthorizationWithoutBuyerFailsClosed() {
        var service = new ValidateBuyerResourceAuthorizationService(new ValidateUserAuthorizationService());
        User user = new User("user-1", "Buyer", new Email("buyer@example.com"), UserStatus.ACTIVE, UserRole.BUYER);
        Cart cart = new Cart("cart-1", "buyer-1", java.util.List.of(), CartStatus.ACTIVE);
        assertThrows(DomainException.class, () -> service.execute(user, cart));
    }
}
