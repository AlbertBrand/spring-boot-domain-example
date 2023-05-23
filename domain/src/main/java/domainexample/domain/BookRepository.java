package domainexample.domain;

import domainexample.domain.port.BookPersistencePort;
import domainexample.domain.port.BookServicePort;

import java.util.List;
import java.util.Optional;

/**
 * Repository
 */
public class BookRepository implements BookServicePort {
    private final BookPersistencePort persistence;

    public BookRepository(BookPersistencePort persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<Book> getBooks() {
        return persistence.getBooks();
    }

    @Override
    public Optional<Book> getBookByIsbn(ISBN isbn) {
        return persistence.getBookByIsbn(isbn);
    }

    @Override
    public Book storeBook(Book book) {
        return persistence.storeBook(book);
    }

    @Override
    public void removeBook(Book book) {
        persistence.removeBook(book);
    }
}
