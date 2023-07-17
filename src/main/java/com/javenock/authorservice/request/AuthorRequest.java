package com.javenock.authorservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {
    @NotBlank
    private String author_name;
    @NotBlank
    private String nationality;
}
