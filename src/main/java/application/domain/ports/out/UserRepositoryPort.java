package application.domain.ports.out;

import application.domain.models.User;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(String userId);
    boolean existsById(String userId);
    boolean existsByEmail(String normalizedEmail);
    User save(User user);
}
