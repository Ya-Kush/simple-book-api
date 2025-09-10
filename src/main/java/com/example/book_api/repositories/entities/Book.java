package com.example.book_api.repositories.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = Book.NAME)
@Accessors(chain = true, makeFinal = true)
@Getter @Setter @ToString(includeFieldNames = false)
public final class Book implements Serializable {
    static final String NAME = "books";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Isbn is required")
    @Pattern(regexp = "^\\d{10}(\\d{3})?$", message = "ISBN must has 10 or 13 digits.")
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull(message = "PublicationDate is required")
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @NotNull(message = "Price is required")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Nullable
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "created_at", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() { updatedAt = createdAt = LocalDateTime.now(); }

    @PreUpdate
    private void onUpdate() { updatedAt = LocalDateTime.now(); }
}
