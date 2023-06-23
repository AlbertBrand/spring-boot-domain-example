package domainexample.adapter.web;

import domainexample.adapter.web.dto.BookDto;
import domainexample.domain.Book;
import domainexample.domain.ValidationException;
import domainexample.domain.port.BookServicePort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
  @Autowired
  private BookServicePort servicePort;

  @GetMapping("/")
  public List<BookDto> getAllBooks() {
    return servicePort.getBooks().stream().map(BookDto::fromDomain).toList();
  }

  @GetMapping("/{isbn}")
  public BookDto getBookByIsbn(@PathVariable String isbn) {
    Optional<Book> book;

    try {
      book = servicePort.getBookByIsbn(isbn);
    } catch (ValidationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Invalid ISBN: " + isbn);
    }

    if (book.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Book with ISBN " + isbn + " not found");
    }

    return BookDto.fromDomain(book.get());
  }

  @PostMapping("/{isbn}")
  public BookDto storeBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
    if (!Objects.equals(bookDto.getIsbn(), isbn)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "ISBN in path and body are not the same");
    }

    try {
      var book = servicePort.storeBook(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthor());
      return BookDto.fromDomain(book);

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Store failed: " + e.getMessage());
    }
  }

  @DeleteMapping("/{isbn}")
  public void deleteBookByID(@PathVariable String isbn) throws ValidationException {
    servicePort.removeBook(isbn);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = Exception.class)
  public Error defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    // rethrow if the exception is already annotated as response status
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null ||
        e instanceof ErrorResponse) {
      throw e;
    }

    return Error.of(e.getMessage());
  }

  @Data
  @AllArgsConstructor(staticName = "of")
  public static class Error {
    private String message;
  }
}
