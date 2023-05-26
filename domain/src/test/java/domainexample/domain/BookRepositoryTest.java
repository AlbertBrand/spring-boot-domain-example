package domainexample.domain;

import domainexample.domain.port.BookPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {
    @Mock
    private BookPersistencePort persistence;

    @InjectMocks
    BookRepository repository;

    @Test
    public void getBooks() {
        repository.getBooks();

        verify(persistence, times(1)).getBooks();
    }

    @Test
    public void getBookByIsbn() {
        var isbn = ISBN.of("978-3866801929");

        repository.getBookByIsbn(isbn);

        verify(persistence, times(1)).getBookByIsbn(isbn);
    }

    @Test
    public void storeBook() {
        var book = Book.of(ISBN.of("978-3866801929"), "title", Author.of("author"));

        repository.storeBook(book);

        verify(persistence, times(1)).storeBook(book);
    }

    @Test
    public void removeBook() {
        var book = Book.of(ISBN.of("978-3866801929"), "title", Author.of("author"));

        repository.removeBook(book);

        verify(persistence, times(1)).removeBook(book);
    }
}
