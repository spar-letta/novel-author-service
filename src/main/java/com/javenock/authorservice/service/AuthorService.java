package com.javenock.authorservice.service;

import com.javenock.authorservice.exception.NoAuthorsFoundException;
import com.javenock.authorservice.exception.NoSuchAuthorException;
import com.javenock.authorservice.model.Author;
import com.javenock.authorservice.repository.AuthorRepository;
import com.javenock.authorservice.request.AuthorRequest;
import com.javenock.authorservice.response.AuthorBookResponse;
import com.javenock.authorservice.response.AuthorResponse;
import com.javenock.authorservice.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private WebClient.Builder webClient;

    public Author createNewAuthor(AuthorRequest authorRequest) {
        Author author = Author.builder()
                .author_name(authorRequest.getAuthor_name())
                .nationality(authorRequest.getNationality())
                .build();
        return authorRepository.save(author);
    }

    public List<Author> printAllAuthors() throws NoAuthorsFoundException {
        List<Author> all_authors = authorRepository.findAll();
        if(all_authors.size() > 0){
            return all_authors;
        }else {
            throw new NoAuthorsFoundException("No Authors Found.");
        }
    }

    public AuthorBookResponse getAuthorById(Long authorid) throws NoSuchAuthorException {
        Author author = authorRepository.findById(authorid).orElseThrow(() -> new NoSuchAuthorException("No such author with id :"+ authorid));
        BookResponse[] bkResponse = webClient.build().get()
                        .uri("http://BOOKS-SERVICE/book/author_id/"+authorid)
                                .retrieve()
                                        .bodyToMono(BookResponse[].class)
                                                .block();
        List<BookResponse> arr = Arrays.asList(bkResponse);
        AuthorBookResponse authorBookResponse = new AuthorBookResponse(new AuthorResponse(author.getAuthor_name(), author.getNationality()), arr);
        return authorBookResponse;
    }
}
