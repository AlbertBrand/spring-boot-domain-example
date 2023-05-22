package domainexample.adapter.web;

import domainexample.adapter.web.dto.BookDto;
import domainexample.domain.port.BookServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServicePort servicePort;

    @PostMapping("/{isbn}")
    public BookDto storeBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        if (!Objects.equals(bookDto.getIsbn(), isbn)) {
            throw new IllegalArgumentException("ISBN in path and body are not the same");
        }
        return BookDto.fromDomain(servicePort.storeBook(bookDto.toDomain()));
    }

    @GetMapping("/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn) {
        return BookDto.fromDomain(servicePort.getBookByIsbn(isbn).orElseThrow());
    }

    @GetMapping("/")
    public List<BookDto> getAllBooks() {
        return servicePort.getBooks().stream().map(BookDto::fromDomain).toList();
    }

    @DeleteMapping("/{isbn}")
    public void deleteBookByID(@PathVariable String isbn) {
        var book = servicePort.getBookByIsbn(isbn).orElseThrow();
        servicePort.removeBook(book);
    }
}
