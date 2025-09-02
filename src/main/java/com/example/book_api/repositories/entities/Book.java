package com.example.book_api.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, makeFinal = true)
@Getter @Setter @ToString(includeFieldNames = false)
public final class Book extends EntityBase {
    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Isbn is required")
    @Column(unique = true, nullable = false)
    private String isbn;

    @NotBlank(message = "PublicationDate is required")
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @NotBlank(message = "Price is required")
    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @ToString.Include(rank = 1)
    private String toStringSupersId() {
        return "%d".formatted(super.getId());
    }

    @ToString.Include
    private String toStringSupersOtherMembers() {
        return "%s, %s, %s".formatted(super.getCreatedAt(), super.getUpdatedAt(), super.getDeletedAt());
    }
}
