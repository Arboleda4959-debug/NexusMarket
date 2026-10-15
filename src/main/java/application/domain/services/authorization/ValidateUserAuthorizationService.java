package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.User;
import application.domain.valueobjects.UserStatus;

public class ValidateUserAuthorizationService {
    public void execute(User user) {
        if (user == null) throw new DomainException("User is required.");
        if (blank(user.getId())) throw new DomainException("User identifier is required.");
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new DomainException("User is not active and cannot perform this operation.");
        }
        if (user.getRole() == null) throw new DomainException("User role is required.");
    }

    private boolean blank(String value) { return value == null || value.isBlank(); }
}
