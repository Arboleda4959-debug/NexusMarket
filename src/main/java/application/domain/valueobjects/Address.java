package application.domain.valueobjects;

public record Address(
        String street,
        String city,
        String postalCode,
        String country) {

    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be blank");
        }
        street = street.trim();
        city = city.trim();
        postalCode = postalCode == null ? "" : postalCode.trim();
        country = country.trim();
    }
}
