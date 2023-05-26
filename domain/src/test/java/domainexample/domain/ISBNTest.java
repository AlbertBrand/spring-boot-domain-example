package domainexample.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ISBNTest {
    @Test
    public void valid() {
        var isbn = ISBN.of("978-3866801929");
        assertThat(isbn.getValue()).isEqualTo("978-3866801929");
    }

    @Test
    public void invalid() {
        assertThatThrownBy(() -> ISBN.of("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid ISBN format");
    }
}
