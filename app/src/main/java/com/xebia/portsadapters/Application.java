package com.xebia.portsadapters;

import com.xebia.portsadapters.domain.Author;
import com.xebia.portsadapters.domain.Book;
import com.xebia.portsadapters.domain.port.BookPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookPersistencePort adapter) {
        return (args) -> {
            Author ericEvansAuthor = Author.of("Eric Evans");
            adapter.storeBook(Book.of("978-0321125217", "Domain-Driven Design: Tackling Complexity in the Heart of Software", ericEvansAuthor));
            adapter.storeBook(Book.of("978-1457501197", "Domain-Driven Design Reference: Definitions and Pattern Summaries", ericEvansAuthor));
            adapter.storeBook(Book.of("978-0321834577", "Implementing Domain Driven Design", Author.of("Vaughn Vernon")));

            var book = adapter.getBookByIsbn("978-0321125217");
            log.info("Single book: {}", book);

            var books = adapter.getBooks();
            for (var b : books) {
                log.info("Book: {}", b);
            }
        };
    }


}
