package com.javenock.authorservice.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookResponse {
    private Long bookid;
    private String book_title;
    private LocalDate date_published;
}
