package com.example.book_api.controllers.models;

import com.example.book_api.repositories.entities.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "The whole book info")
public record WholeBookResponse(
        @Schema(description = "The ISBN of the book", example = "9876543210420")
        String isbn,

        @Schema(description = "The title of the book", example = "War and Peace")
        String title,

        @Schema(description = "The author of the book", example = "Leo Nikolaevich Tolstoy")
        String author,

        @Schema(description = "The price of the book", example = "69.69")
        BigDecimal price,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Schema(description = "The publication date of the book", example = "2000-12-31")
        LocalDate publicationDate,

        @Nullable
        @Schema(description = "The description of the book", example = "The same popular book you should read!", nullable = true)
        String description
) {
    public static WholeBookResponse from(Book book) {
        return new WholeBookResponse(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublicationDate(), book.getDescription());
    }
}
