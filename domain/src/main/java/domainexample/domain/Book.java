package domainexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity
 */
@Data
@AllArgsConstructor(staticName = "of")
public class Book {
    private ISBN isbn;
    private String title;
    private Author author;
}
