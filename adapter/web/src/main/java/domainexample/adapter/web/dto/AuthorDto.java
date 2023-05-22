package domainexample.adapter.web.dto;

import domainexample.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class AuthorDto {
    private final String name;

    public static AuthorDto fromDomain(Author author) {
        return AuthorDto.builder().name(author.getName()).build();
    }

    public Author toDomain() {
        return Author.of(name);
    }
}
