package domainexample.adapter.jpa.entity;

import domainexample.domain.Book;
import domainexample.domain.ISBN;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    private String isbn;
    private String title;
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AuthorEntity author;

    public static BookEntity fromDomain(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn().getValue())
                .title(book.getTitle())
                .author(AuthorEntity.fromDomain(book.getAuthor()))
                .build();
    }

    public Book toDomain() {
        return Book.of(ISBN.of(isbn), title, author.toDomain());
    }
}
