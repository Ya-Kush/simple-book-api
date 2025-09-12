package com.example.book_api.controllers;

import com.example.book_api.controllers.models.*;
import com.example.book_api.exceptions.BookAlreadyExistException;
import com.example.book_api.services.BookManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController implements BookRestControllerApi {
    public BookRestController(BookManager bm) {
        bookManager = bm;
    }

    BookManager bookManager;

    @Override
    public ResponseEntity<PartialBookResponse> createBook(WholeBookRequest req) {
        var book = req.toBook();
        bookManager.save(book);
        return new ResponseEntity<>(PartialBookResponse.from(book), HttpStatus.CREATED);
    }
    @ExceptionHandler(BookAlreadyExistException.class)
    public ExceptionResponse handleBookAlreadyExist(BookAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.CONFLICT)
                .put("error", e.getMessage());
    }

    @Override
    public ResponseEntity<WholeBookResponse> getBookByIsbn(String isbn) {
        var book = bookManager.findByIsbn(isbn);
        return book.map(value -> new ResponseEntity<>(WholeBookResponse.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteBookByIsbn(String isbn) {
        var res = bookManager.deleteByIsbn(isbn);
        return res ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                   : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
