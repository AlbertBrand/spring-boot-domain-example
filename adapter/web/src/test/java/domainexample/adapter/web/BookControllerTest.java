package domainexample.adapter.web;

import com.fasterxml.jackson.databind.json.JsonMapper;

import domainexample.adapter.web.config.SecurityConfig;
import domainexample.adapter.web.dto.BookDto;
import domainexample.domain.Author;
import domainexample.domain.Book;
import domainexample.domain.ISBN;
import domainexample.domain.port.BookServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
@ContextConfiguration(classes = BookController.class)
@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    BookServicePort servicePort;

    private final JsonMapper objectMapper = new JsonMapper();

    @Test
    public void getAllBooks() throws Exception {
        given(servicePort.getBooks()).willReturn(List.of(
                Book.of(ISBN.of("123-1234567895"), "title", Author.of("author")),
                Book.of(ISBN.of("234-1234567890"), "title2", Author.of("author2"))
        ));

        mvc.perform(MockMvcRequestBuilders
                        .get("/book/")
                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value("123-1234567895"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isbn").value("234-1234567890"));
    }

    @Test
    public void getBookByIsbn() throws Exception {
        given(servicePort.getBookByIsbn(ISBN.of("123-1234567895"))).willReturn(Optional.of(
                Book.of(ISBN.of("123-1234567895"), "title", Author.of("author"))
        ));

        mvc.perform(MockMvcRequestBuilders
                        .get("/book/123-1234567895")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("123-1234567895"));
    }

    @Test
    public void getBookByIsbn_incorrectParam() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/book/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBookByIsbn_noResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/book/123-1234567895")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void storeBook_correctIsbn() throws Exception {
        var book = Book.of(ISBN.of("123-1234567895"), "title", Author.of("author"));
        given(servicePort.storeBook(book)).willReturn(book);

        mvc.perform(MockMvcRequestBuilders
                        .post("/book/123-1234567895")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(BookDto.fromDomain(book)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("123-1234567895"));
    }

    @Test
    public void storeBook_incorrectIsbn() throws Exception {
        var book = Book.of(ISBN.of("123-1234567895"), "title", Author.of("author"));

        mvc.perform(MockMvcRequestBuilders
                        .post("/book/234-1234567895")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(BookDto.fromDomain(book)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBookByID() throws Exception {
        var book = Book.of(ISBN.of("123-1234567895"), "title", Author.of("author"));
        given(servicePort.getBookByIsbn(ISBN.of("123-1234567895"))).willReturn(Optional.of(book));

        mvc.perform(MockMvcRequestBuilders
                        .delete("/book/123-1234567895")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

