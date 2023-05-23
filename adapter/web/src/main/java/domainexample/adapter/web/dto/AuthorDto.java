package domainexample.adapter.web.dto;

import domainexample.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AuthorDto {
    private String name;

    public static AuthorDto fromDomain(Author author) {
        return AuthorDto.builder().name(author.getName()).build();
    }

    public Author toDomain() {
        return Author.of(name);
    }
}
