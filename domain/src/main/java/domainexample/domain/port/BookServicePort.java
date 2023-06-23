package domainexample.domain.port;

import domainexample.domain.Book;
import domainexample.domain.ValidationException;

import java.util.List;
import java.util.Optional;

public interface BookServicePort {
    List<Book> getBooks();

    Optional<Book> getBookByIsbn(String isbn) throws ValidationException;

    Book storeBook(String isbn, String title, String authorLastName) throws ValidationException;

    void removeBook(String isbn) throws ValidationException;
}
