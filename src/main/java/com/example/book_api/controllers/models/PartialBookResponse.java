package com.example.book_api.controllers.models;

import com.example.book_api.repositories.entities.Book;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "The partial book info")
public record PartialBookResponse(
        @Schema(description = "The ISBN of the book", example = "9876543210420")
        String isbn,

        @Schema(description = "The title of the book", example = "War and Peace")
        String title,

        @Schema(description = "The author of the book", example = "Leo Nikolaevich Tolstoy")
        String author,

        @Schema(description = "The price of the book", example = "69.69")
        BigDecimal price
) {
    public static PartialBookResponse from(Book book) {
        return new PartialBookResponse(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice());
    }
}
