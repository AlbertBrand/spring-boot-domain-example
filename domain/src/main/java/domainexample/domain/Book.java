package domainexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity
 */
@Data
@AllArgsConstructor(staticName = "of")
public class Book {
    private String isbn;
    private String title;
    private Author author;
}
