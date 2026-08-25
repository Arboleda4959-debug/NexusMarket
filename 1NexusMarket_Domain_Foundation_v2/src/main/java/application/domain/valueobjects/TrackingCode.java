package application.domain.valueobjects;

public record TrackingCode(String value) {
    public TrackingCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Tracking code cannot be blank");
        }
        value = value.trim();
    }
}
