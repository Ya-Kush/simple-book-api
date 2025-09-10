package com.example.book_api.controllers;

import com.example.book_api.controllers.models.PartialBookResponse;
import com.example.book_api.controllers.models.WholeBookRequest;
import com.example.book_api.services.BookManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
