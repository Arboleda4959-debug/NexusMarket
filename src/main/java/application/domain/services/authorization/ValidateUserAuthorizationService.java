package application.domain.services.authorization;

import application.domain.exceptions.DomainException;
import application.domain.models.User;
import application.domain.valueobjects.UserStatus;

public class ValidateUserAuthorizationService {

    public void execute(User user) {

        if (user == null) {
            throw new DomainException("User must be provided.");
        }

        if (!UserStatus.ACTIVE.equals(user.getStatus())) {
            throw new DomainException(
                    "User is not authorized to perform this operation."
            );
        }
    }
}