package domainexample.domain;

import domainexample.domain.port.BookPersistencePort;
import domainexample.domain.port.BookServicePort;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookServicePort {
    private final BookPersistencePort repository;

    public BookServiceImpl(BookPersistencePort repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getBooks() {
        return repository.getBooks();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return repository.getBookByIsbn(isbn);
    }

    @Override
    public Book storeBook(Book book) {
        return repository.storeBook(book);
    }

    @Override
    public void removeBook(Book book) {
        repository.removeBook(book);
    }
}
