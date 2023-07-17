package com.javenock.authorservice.controller;

import com.javenock.authorservice.exception.NoAuthorsFoundException;
import com.javenock.authorservice.exception.NoSuchAuthorException;
import com.javenock.authorservice.model.Author;
import com.javenock.authorservice.request.AuthorRequest;
import com.javenock.authorservice.response.AuthorBookResponse;
import com.javenock.authorservice.response.BookResponse;
import com.javenock.authorservice.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {


    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody @Valid  AuthorRequest authorRequest){
        return authorService.createNewAuthor(authorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Author> listOfAuthors() throws NoAuthorsFoundException {
        return authorService.printAllAuthors();
    }

    @GetMapping("/{authorid}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorBookResponse getAuthor(@PathVariable Long authorid) throws NoSuchAuthorException {
        return authorService.getAuthorById(authorid);
    }

}
