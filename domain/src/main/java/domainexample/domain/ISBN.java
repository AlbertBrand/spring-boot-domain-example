package domainexample.domain;

import lombok.Value;

/**
 * Value object
 */
@Value
public class ISBN {
    private String value;

    private static final String ISBN_REGEX = "\\d{3}-\\d{10}";

    private ISBN(String value) throws ValidationException {
        if (value == null)
            throw new ValidationException("ISBN value cannot be null");

        if (!value.matches(ISBN_REGEX)) {
            throw new ValidationException("Invalid ISBN format");
        }

        if (!hasValidChecksum(value)) {
            throw new ValidationException("Invalid ISBN checksum digit");
        }

        this.value = value;
    }

    private boolean hasValidChecksum(String value) {
        String isbn = value.replaceAll("-", "");

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == Character.getNumericValue(isbn.charAt(12));
    }

    public static ISBN of(String value) throws ValidationException {
        return new ISBN(value);
    }

    public static ISBN tryParse(String string) {
      try {
        return ISBN.of(string);
      } catch (ValidationException e) {
        throw new RuntimeException("Unexpected validation error: " + e.getMessage());
      }
    }
}
