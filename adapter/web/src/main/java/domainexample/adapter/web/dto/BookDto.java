package domainexample.adapter.web.dto;

import domainexample.domain.Book;
import domainexample.domain.ISBN;
import domainexample.domain.Author;
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
        return BookDto.builder()
                .isbn(book.getIsbn().getValue())
                .title(book.getTitle())
                .author(book.getAuthor().getName())
                .build();
    }

    public Book toDomain() {
        return Book.of(ISBN.of(isbn), title, Author.of(author));
    }

}
