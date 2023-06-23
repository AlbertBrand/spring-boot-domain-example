package domainexample.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BookTest {
  @Test
  void twoBooks_areEqual_byIsbn() {
    var book1 = Book.of(ISBN.of("123-1231231126"), "title", Author.of("authorname"));
    var book2 = Book.of(ISBN.of("123-1231231126"), "title new",  Author.of("authorname"));

    assertTrue(book1.equals(book2));
  }
}
