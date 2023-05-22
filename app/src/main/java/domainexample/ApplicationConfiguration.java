package domainexample;

import domainexample.adapter.jpa.BookJpaAdapter;
import domainexample.domain.BookServiceImpl;
import domainexample.domain.port.BookPersistencePort;
import domainexample.domain.port.BookServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public BookServicePort bookService(BookPersistencePort repository) {
        return new BookServiceImpl(repository);
    }

    @Bean BookPersistencePort bookPersistence() {
        return new BookJpaAdapter();
    }
}
