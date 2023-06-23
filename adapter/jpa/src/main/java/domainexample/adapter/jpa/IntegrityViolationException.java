package domainexample.adapter.jpa;

public class IntegrityViolationException extends RuntimeException {
  public IntegrityViolationException(Exception e) {
    super(e);
  }
}
