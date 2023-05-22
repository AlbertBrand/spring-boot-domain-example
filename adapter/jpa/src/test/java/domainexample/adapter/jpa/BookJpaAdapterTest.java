package domainexample.adapter.jpa;

import domainexample.domain.Author;
import domainexample.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@ContextConfiguration(classes = BookJpaAdapter.class)
@DataJpaTest
public class BookJpaAdapterTest {
    @Autowired
    BookJpaAdapter adapter;

    private final String book1isbn = "978-1457501197";
    private final Author ericEvansAuthor = Author.of("Eric Evans");
    private final Book book1 = Book.of(book1isbn, "Domain-Driven Design Reference: Definitions and Pattern Summaries", ericEvansAuthor);

    @Test
    public void test_getBookByIsbn() {
        adapter.storeBook(book1);

        var book = adapter.getBookByIsbn(book1isbn).orElseThrow();

        assertThat(book.getIsbn()).isEqualTo(book1isbn);
    }

    @Test
    public void test_getBooks() {
        adapter.storeBook(Book.of("978-0321125217", "Domain-Driven Design: Tackling Complexity in the Heart of Software", ericEvansAuthor));
        Author vaughnVernonAuthor = Author.of("Vaughn Vernon");
        adapter.storeBook(Book.of("978-0321834577", "Implementing Domain Driven Design", vaughnVernonAuthor));

        var books = adapter.getBooks();

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getAuthor()).isEqualTo(ericEvansAuthor);
        assertThat(books.get(1).getAuthor()).isEqualTo(vaughnVernonAuthor);
    }

    @Test
    public void test_storeBook() {
        String book2isbn = "978-0321125217";
        String book2title = "Domain-Driven Design: Tackling Complexity in the Heart of Software";
        adapter.storeBook(Book.of(book2isbn, book2title, ericEvansAuthor));

        var book = adapter.getBookByIsbn(book2isbn).orElseThrow();

        assertThat(book.getIsbn()).isEqualTo(book2isbn);
        assertThat(book.getTitle()).isEqualTo(book2title);
        assertThat(book.getAuthor()).isEqualTo(ericEvansAuthor);
    }

    @Test
    public void test_removeBook() {
        adapter.storeBook(Book.of(book1isbn, "Domain-Driven Design Reference: Definitions and Pattern Summaries", Author.of("Eric Evans")));
        var book = adapter.getBookByIsbn(book1isbn);
        assertThat(book.isPresent()).isTrue();

        adapter.removeBook(book.get());

        book = adapter.getBookByIsbn(book1isbn);
        assertThat(book.isPresent()).isFalse();
    }
}
