package com.xebia.portsadapters.infra.entity;

import com.xebia.portsadapters.domain.Author;
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
    public String name;

    public static AuthorEntity fromDomain(Author author) {
        return AuthorEntity.builder().name(author.getName()).build();
    }

    public Author toDomain() {
        return Author.of(name);
    }
}
