package domainexample.adapter.jpa.entity;

import domainexample.domain.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static AuthorEntity fromDomain(Author author) {
        return AuthorEntity.builder().name(author.getName()).build();
    }

    public Author toDomain() {
        return Author.of(name);
    }
}
