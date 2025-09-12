package com.example.book_api.controllers;

import com.example.book_api.controllers.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Books", description = "The API for book management")
public interface BookRestControllerApi extends ApiV1_ControllerBase {
    String JSON = "application/json";

    @PostMapping(path = "books", produces = JSON, consumes = JSON)
    @Operation(summary = "Creating a new book", description = "The API method to create a new book")
    @ApiResponse(responseCode = "201", description = "The book was created", content = @Content(mediaType = JSON, schema = @Schema(implementation = PartialBookResponse.class)))
    @ApiResponse(responseCode = "400", description = "Uncorrected request data", content = @Content(mediaType = JSON, schema = @Schema(implementation = Object.class)))
    @ApiResponse(responseCode = "409", description = "A book with the same ISBN already exists", content = @Content(mediaType = JSON, schema = @Schema(implementation = Object.class)))
    ResponseEntity<PartialBookResponse> createBook(
            @Parameter(description = "The book data", required = true)
            @Valid
            @RequestBody
            WholeBookRequest req);

    @GetMapping(path = "books/{isbn}", produces = JSON)
    @Operation(summary = "Get the book with this isbn", description = "The API method to get the book")
    @ApiResponse(responseCode = "200", description = "The book was found", content = @Content(mediaType = JSON, schema = @Schema(implementation = WholeBookResponse.class)))
    @ApiResponse(responseCode = "400", description = "Uncorrected request data", content = @Content(mediaType = JSON, schema = @Schema(implementation = Object.class)))
    @ApiResponse(responseCode = "404", description = "The book with this ISBN wasn't found", content = @Content(mediaType = JSON, schema = @Schema(implementation = Object.class)))
    ResponseEntity<WholeBookResponse> getBookByIsbn(
            @Parameter(description = "The book's ISBN", example = "9876543210420", required = true)
            @Pattern(regexp = "^\\d{10}(\\d{3})?$", message = "ISBN must has 10 or 13 digits.")
            @PathVariable
            String isbn);

    @DeleteMapping(path = "books/{isbn}")
    @Operation(summary = "Delete the book with this isbn", description = "The API method to delete the book")
    @ApiResponse(responseCode = "204", description = "The book was deleted")
    @ApiResponse(responseCode = "400", description = "Uncorrected request data", content = @Content(mediaType = JSON, schema = @Schema(implementation = Object.class)))
    @ApiResponse(responseCode = "404", description = "The book with this ISBN wasn't found", content = @Content(mediaType = JSON, schema = @Schema(implementation = Object.class)))
    ResponseEntity<Void> deleteBookByIsbn(
            @Parameter(description = "The book's ISBN", example = "9876543210420", required = true)
            @Pattern(regexp = "^\\d{10}(\\d{3})?$", message = "ISBN must has 10 or 13 digits.")
            @PathVariable
            String isbn);
}
