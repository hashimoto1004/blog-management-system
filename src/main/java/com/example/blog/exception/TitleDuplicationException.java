package com.example.blog.exception;

public class TitleDuplicationException extends RuntimeException {
    public TitleDuplicationException(String message) {
        super(message);
    }
}
