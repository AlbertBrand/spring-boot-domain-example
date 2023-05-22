package domainexample.adapter.web.dto;

import domainexample.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class BookDto {
    private String isbn;
    private String title;
    private AuthorDto author;

    public static BookDto fromDomain(Book book) {
        return BookDto.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(AuthorDto.fromDomain(book.getAuthor()))
                .build();
    }

    public Book toDomain() {
        return Book.of(isbn, title, author.toDomain());
    }

}
