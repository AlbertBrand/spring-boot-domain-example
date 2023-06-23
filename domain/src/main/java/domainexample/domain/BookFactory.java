package domainexample.domain;

public class BookFactory {

  public static Book createBook(String isbnString, String title, String authorLastName) throws ValidationException {
    var isbn = ISBN.of(isbnString);
    var author = Author.of(authorLastName);
    return Book.of(isbn, title, author);
  }

}
