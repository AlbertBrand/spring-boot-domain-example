package domainexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import domainexample.domain.Author;
import domainexample.domain.Book;
import domainexample.domain.ISBN;
import domainexample.domain.port.BookPersistencePort;

@Configuration
@Profile("local")
public class LocalConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(BookPersistencePort persistence) {
        return (args) -> {
            // initialise database with test data on local profile

            var ericEvansAuthor = Author.of("Eric Evans");
            persistence.storeBook(Book.of(ISBN.of("978-1457501197"),
                    "Domain-Driven Design Reference: Definitions and Pattern Summaries", ericEvansAuthor));
            persistence.storeBook(Book.of(ISBN.of("978-0321125217"),
                    "Domain-Driven Design: Tackling Complexity in the Heart of Software", ericEvansAuthor));
            persistence.storeBook(Book.of(ISBN.of("978-0321834577"), "Implementing Domain Driven Design",
                    Author.of("Vaughn Vernon")));
        };
    }
}
