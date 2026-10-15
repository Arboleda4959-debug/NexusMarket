package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.User;
import application.domain.ports.out.UserRepositoryPort;
import application.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ValidateUserUniquenessServiceTest {
    private User user() { return new User("user-1", "User", new Email("user@example.com"), UserStatus.ACTIVE, UserRole.BUYER); }
    private UserRepositoryPort repository(boolean idExists, boolean emailExists) {
        return new UserRepositoryPort() {
            public Optional<User> findById(String id) { return Optional.empty(); }
            public boolean existsById(String id) { return idExists; }
            public boolean existsByEmail(String email) { return emailExists; }
            public User save(User user) { return user; }
        };
    }
    @Test void acceptsUniqueUser() { assertDoesNotThrow(() -> new ValidateUserUniquenessService(repository(false, false)).execute(user())); }
    @Test void rejectsDuplicateEmail() { assertThrows(DomainException.class, () -> new ValidateUserUniquenessService(repository(false, true)).execute(user())); }
}
