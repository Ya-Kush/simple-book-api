package com.example.book_api.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = Book.NAME)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, makeFinal = true)
@Getter @Setter @ToString(includeFieldNames = false)
public final class Book {
    static final String NAME = "books";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "Isbn is required")
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @NotBlank(message = "PublicationDate is required")
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @NotBlank(message = "Price is required")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false)
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
