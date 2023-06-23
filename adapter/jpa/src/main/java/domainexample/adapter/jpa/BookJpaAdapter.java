package domainexample.adapter.jpa;

import domainexample.adapter.jpa.entity.BookEntity;
import domainexample.adapter.jpa.repository.BookRepository;
import domainexample.domain.Book;
import domainexample.domain.BookFactory;
import domainexample.domain.ISBN;
import domainexample.domain.ValidationException;
import domainexample.domain.port.BookPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookJpaAdapter implements BookPersistencePort {
  @Autowired
  BookRepository repository;

  @Override
  public List<Book> getBooks() {
    return repository.findAll().stream().map(this::toDomain).toList();
  }

  @Override
  public Optional<Book> getBookByIsbn(ISBN isbn) {
    return repository.findById(isbn.getValue()).map(this::toDomain);
  }

  @Override
  public Book storeBook(Book book) {
    return toDomain(repository.saveAndFlush(BookEntity.fromDomain(book)));
  }

  @Override
  public void removeBook(ISBN isbn) {
    repository.deleteById(isbn.getValue());
    repository.flush();
  }

  private Book toDomain(BookEntity entity) {
    try {
      return BookFactory.createBook(entity.getIsbn(), entity.getTitle(), entity.getAuthor().getName());
    } catch (ValidationException e) {
      throw new IntegrityViolationException(e);
    }
  }
}
