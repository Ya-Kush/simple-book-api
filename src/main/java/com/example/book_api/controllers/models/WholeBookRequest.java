package com.example.book_api.controllers.models;

import com.example.book_api.repositories.entities.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "The request to create a whole book")
public record WholeBookRequest(
        @Pattern(regexp = "^\\d{10}(\\d{3})?$", message = "ISBN must has 10 or 13 digits.")
        @Schema(description = "The ISBN of the book", example = "9876543210420")
        String isbn,

        @NotBlank(message = "Title is required")
        @Schema(description = "The title of the book", example = "War and Peace")
        String title,

        @NotBlank(message = "Author is required")
        @Schema(description = "The author of the book", example = "Leo Nikolaevich Tolstoy")
        String author,

        @NotNull(message = "Price is required")
        @Schema(description = "The price of the book", example = "69.69")
        BigDecimal price,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "PublicationDate is required")
        @Schema(description = "The publication date of the book", example = "2000-12-31")
        LocalDate publicationDate,

        @Nullable
        @Schema(description = "The description of the book", example = "The same popular book you should read!", nullable = true)
        String description
) {
    public Book toBook() {
        return new Book()
                .setIsbn(isbn)
                .setTitle(title)
                .setAuthor(author)
                .setPrice(price)
                .setPublicationDate(publicationDate)
                .setDescription(description);
    }
}
