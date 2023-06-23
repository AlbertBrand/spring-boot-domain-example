package domainexample.adapter.web.dto;

import domainexample.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private String author;

    public static BookDto fromDomain(Book book) {
        return BookDto.of(
          book.getIsbn().getValue(),
          book.getTitle(),
          book.getAuthor().getName());
    }
}
