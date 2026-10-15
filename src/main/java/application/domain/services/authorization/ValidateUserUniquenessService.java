package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.User;
import application.domain.ports.out.UserRepositoryPort;
import java.util.Objects;

/** Checks uniqueness against persisted state; database constraints must back this check. */
public class ValidateUserUniquenessService {
    private final UserRepositoryPort users;
    public ValidateUserUniquenessService(UserRepositoryPort users) { this.users = Objects.requireNonNull(users); }
    public void execute(User user) {
        if (user == null) throw new DomainException("User is required.");
        if (user.getId() == null || user.getId().isBlank()) throw new DomainException("User identifier is required.");
        if (user.getEmail() == null || user.getEmail().value() == null || user.getEmail().value().isBlank()) throw new DomainException("User email is required.");
        if (users.existsById(user.getId())) throw new DomainException("User identifier already exists.");
        if (users.existsByEmail(user.getEmail().value())) throw new DomainException("User email already exists.");
    }
}
