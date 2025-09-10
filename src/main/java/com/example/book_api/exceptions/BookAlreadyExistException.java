package com.example.book_api.exceptions;

public class BookAlreadyExistException extends BadRequestException {
    public BookAlreadyExistException(String msg) { super(msg); }
    public BookAlreadyExistException(String msg, Exception e) { super(msg, e); }
}
