package com.javenock.authorservice.advice;

import com.javenock.authorservice.exception.NoAuthorsFoundException;
import com.javenock.authorservice.exception.NoSuchAuthorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((er) -> {
            errorMap.put(er.getField(), er.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchAuthorException.class)
    public Map<String, String> emptyBookListHandler(NoSuchAuthorException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Author service error :", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoAuthorsFoundException.class)
    public Map<String, String> emptyBookListHandler(NoAuthorsFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Author service error :", ex.getMessage());
        return errorMap;
    }
}
