package com.example.blog.exception;

public class ContentEmptyException extends RuntimeException {
    public ContentEmptyException(String message) {
        super(message);
    }
}
