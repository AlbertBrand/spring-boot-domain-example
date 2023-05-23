package domainexample.domain.port;

import domainexample.domain.Book;
import domainexample.domain.ISBN;

import java.util.List;
import java.util.Optional;

public interface BookServicePort {
    List<Book> getBooks();

    Optional<Book> getBookByIsbn(ISBN isbn);

    Book storeBook(Book book);

    void removeBook(Book book);
}
