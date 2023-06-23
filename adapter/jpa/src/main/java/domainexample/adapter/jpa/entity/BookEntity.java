package domainexample.adapter.jpa.entity;

import domainexample.domain.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BookEntity {
  @Id
  private String isbn;
  private String title;
  @ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  private AuthorEntity author;

  public static BookEntity fromDomain(Book book) {
    return BookEntity.of(
        book.getIsbn().getValue(),
        book.getTitle(),
        AuthorEntity.fromDomain(book.getAuthor()));
  }
}
