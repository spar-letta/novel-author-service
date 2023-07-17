package com.javenock.authorservice.exception;

public class NoAuthorsFoundException extends Exception{

    public NoAuthorsFoundException(String message) {
        super(message);
    }
}
