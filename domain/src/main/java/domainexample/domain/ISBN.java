package domainexample.domain;

import lombok.Value;

/**
 * Value object
 */
@Value
public class ISBN {
    String value;

    private ISBN(String value) {
        if (value == null) throw new NullPointerException("value");

        if (!value.matches("[0-9]{3}-[0-9]{10}")) {
            throw new IllegalArgumentException("Invalid ISBN format");
        }

        this.value = value;
    }

    public static ISBN of(String value) {
        return new ISBN(value);
    }
}
