package application.domain.ports.in;

import application.domain.models.Buyer;
import application.domain.models.User;

/** Public registration contract; credential storage/authentication is outside this domain-only stage. */
public interface PublicAccessPort {
    void registerBuyer(User user, Buyer buyer);
}
