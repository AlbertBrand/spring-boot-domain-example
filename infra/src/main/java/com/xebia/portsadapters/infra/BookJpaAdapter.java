package com.xebia.portsadapters.infra;

import com.xebia.portsadapters.domain.Book;
import com.xebia.portsadapters.domain.port.BookPersistencePort;
import com.xebia.portsadapters.infra.entity.BookEntity;
import com.xebia.portsadapters.infra.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookJpaAdapter implements BookPersistencePort {
    @Autowired
    BookRepository repository;

    @Override
    public List<Book> getBooks() {
        return repository.findAll().stream().map(BookEntity::toDomain).toList();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return repository.findById(isbn).map(BookEntity::toDomain);
    }

    @Override
    public Book storeBook(Book book) {
        return repository.saveAndFlush(BookEntity.fromDomain(book)).toDomain();
    }

    @Override
    public void removeBook(Book book) {
        repository.delete(BookEntity.fromDomain(book));
    }
}
