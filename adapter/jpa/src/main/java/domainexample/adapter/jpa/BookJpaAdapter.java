package domainexample.adapter.jpa;

import domainexample.adapter.jpa.entity.BookEntity;
import domainexample.adapter.jpa.repository.BookRepository;
import domainexample.domain.Book;
import domainexample.domain.ISBN;
import domainexample.domain.port.BookPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookJpaAdapter implements BookPersistencePort {
    @Autowired
    BookRepository repository;

    @Override
    public List<Book> getBooks() {
        return repository.findAll().stream().map(BookEntity::toDomain).toList();
    }

    @Override
    public Optional<Book> getBookByIsbn(ISBN isbn) {
        return repository.findById(isbn.getValue()).map(BookEntity::toDomain);
    }

    @Override
    public Book storeBook(Book book) {
        return repository.saveAndFlush(BookEntity.fromDomain(book)).toDomain();
    }

    @Override
    public void removeBook(Book book) {
        repository.delete(BookEntity.fromDomain(book));
        repository.flush();
    }
}
