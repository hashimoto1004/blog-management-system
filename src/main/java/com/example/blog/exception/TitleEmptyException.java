package com.example.blog.exception;

public class TitleEmptyException extends RuntimeException {
    public TitleEmptyException(String message) {
        super(message);
    }
}
