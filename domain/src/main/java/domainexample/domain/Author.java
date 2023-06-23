package domainexample.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Value object
 */
@Value
@AllArgsConstructor(staticName = "of")
public class Author {
    private final String name;
}
