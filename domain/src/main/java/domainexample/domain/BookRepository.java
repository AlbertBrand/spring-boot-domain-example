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
  public Optional<Book> getBookByIsbn(String isbn) throws ValidationException {
    return persistence.getBookByIsbn(ISBN.of(isbn));
  }

  @Override
  public Book storeBook(String isbn, String title, String authorLastName) throws ValidationException {
    Book book = BookFactory.createBook(isbn, title, authorLastName);
    return persistence.storeBook(book);
  }

  @Override
  public void removeBook(String isbn) throws ValidationException {
    persistence.removeBook(ISBN.of(isbn));
  }
}
