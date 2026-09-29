package application.domain.exceptions;

/**
 * Base exception for business rules.
 * Specific domain exceptions will be added when the detailed service specifications are defined.
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
