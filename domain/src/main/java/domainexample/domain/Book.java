package domainexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity
 */
@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "isbn")
public class Book {
  private ISBN isbn;
  private String title;
  private Author author;

  void changeAuthor(String lastName) {
    author = Author.of(lastName);
  }
}
