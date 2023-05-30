package domainexample.adapter.jpa.repository;

import domainexample.adapter.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {

}