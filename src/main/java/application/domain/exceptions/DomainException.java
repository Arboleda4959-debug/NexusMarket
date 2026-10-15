package application.domain.exceptions;

/**
 * Base exception for business rules.
 * Specific domain exceptions will be added when the detailed service specifications are defined.
 */
public class DomainException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DomainException(String message) {
        super(message);
    }
}
