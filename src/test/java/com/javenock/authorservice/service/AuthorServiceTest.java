package com.javenock.authorservice.service;

import com.javenock.authorservice.exception.NoAuthorsFoundException;
import com.javenock.authorservice.model.Author;
import com.javenock.authorservice.repository.AuthorRepository;
import com.javenock.authorservice.request.AuthorRequest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private WebClient webClient;

    @Test
    @DisplayName("save_author")
    public void shouldSaveAuthor(){
        AuthorRequest authorRequest = new AuthorRequest("Peter chemia","kenyan");
        Author author = new Author(1L, authorRequest.getAuthor_name(), authorRequest.getNationality());
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        Author savedAuthor = authorService.createNewAuthor(authorRequest);
        assertNotNull(savedAuthor);
        assertThat(savedAuthor.getAuthor_name()).isEqualTo("Peter chemia");
    }

    @Test
    @DisplayName("Throw_exception_No_Authors")
    public void shouldThrowException() throws NoAuthorsFoundException {
        AuthorRequest authorRequest = new AuthorRequest();
        List<Author> author = new ArrayList<>();
        when(authorRepository.findAll()).thenReturn(author);
        assertThrows(NoAuthorsFoundException.class, () -> authorService.printAllAuthors());
    }

//    @Test
//    @DisplayName("Fetch_Author_With_Books")
//    public void shouldFetchAuthorWithBooks() throws NoSuchAuthorException {
//        Author author = new Author(1L, "Cynthis Peri", "Kenyan");
//        BookResponse[] bkResponse = new BookResponse[1];
//        when(webClient.get()
//                .uri("http://BOOKS-SERVICE/book/author_id/"+ 1L)
//                .retrieve()
//                .bodyToMono(BookResponse[].class)
//                .block()).thenReturn(bkResponse);
//        List<BookResponse> arr = Arrays.asList(bkResponse);
//        AuthorBookResponse authorBookR = new AuthorBookResponse(new AuthorResponse(author.getAuthor_name(), author.getNationality()), arr);
//        authorBookR = authorService.getAuthorById(1L);
//        assertThat(authorBookR.getAuthorResponse().getAuthor_name()).isEqualTo("Cynthis Peri");
//        assertThat((authorBookR.getBookResponse()).size()).isEqualTo(1);
//
//    }

}
