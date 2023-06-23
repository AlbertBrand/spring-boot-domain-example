package domainexample.domain;

public class ValidationException extends Exception {
  ValidationException(String message) {
    super(message);
  }
}
