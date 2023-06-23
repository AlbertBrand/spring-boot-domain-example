package domainexample.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ISBNTest {
    @Test
    public void valid() {
        var isbn = ISBN.tryParse("978-1457501197");
        assertThat(isbn.getValue()).isEqualTo("978-1457501197");
    }

    @Test
    public void invalid_isbn() {
        assertThatThrownBy(() -> ISBN.of("abc"))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Invalid ISBN format");
    }

    @Test
    public void invalid_checksum() {
        assertThatThrownBy(() -> ISBN.of("978-1457501198"))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Invalid ISBN checksum digit");
    }
}
