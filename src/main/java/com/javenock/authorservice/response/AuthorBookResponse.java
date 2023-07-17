package com.javenock.authorservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorBookResponse {
    private AuthorResponse authorResponse;
    private List<BookResponse> bookResponse;
}
