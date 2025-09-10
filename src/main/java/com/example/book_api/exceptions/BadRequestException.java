package com.example.book_api.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) { super(msg); }
    public BadRequestException(String msg, Exception e) { super(msg, e); }
}
