package domainexample.domain;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domainexample.domain.port.BookPersistencePort;

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
    public void getBookByIsbn() throws ValidationException {
        var isbn = ISBN.of("978-3866801929");

        repository.getBookByIsbn(isbn.getValue());

        verify(persistence, times(1)).getBookByIsbn(isbn);
    }

    @Test
    public void storeBook() throws ValidationException {
        var book = Book.of(ISBN.of("978-3866801929"), "title", Author.of("author"));

        repository.storeBook(book.getIsbn().getValue(), book.getTitle(), book.getAuthor().getName());

        verify(persistence, times(1)).storeBook(book);
    }

    @Test
    public void removeBook() throws ValidationException {
        var isbn = ISBN.of("978-3866801929");

        repository.removeBook(isbn.getValue());

        verify(persistence, times(1)).removeBook(isbn);
    }
}
