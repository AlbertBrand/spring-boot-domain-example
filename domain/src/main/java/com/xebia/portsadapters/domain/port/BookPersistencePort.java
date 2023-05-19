package com.xebia.portsadapters.domain.port;

import com.xebia.portsadapters.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookPersistencePort {
    List<Book> getBooks();

    Optional<Book> getBookByIsbn(String isbn);

    Book storeBook(Book book);

    void removeBook(Book book);
}
