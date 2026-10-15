package application.domain.ports.in;

import application.domain.models.Seller;
import application.domain.models.User;

public interface AdministratorPort {
    void validateSellerRegistration(User administrator, Seller seller);
}
