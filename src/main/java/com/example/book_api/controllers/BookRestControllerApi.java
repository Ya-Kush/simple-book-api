package com.example.book_api.controllers;

import com.example.book_api.controllers.models.WholeBookRequest;
import com.example.book_api.controllers.models.PartialBookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Books", description = "The API for book management")
public interface BookRestControllerApi {
    @Operation(summary = "Creating a new book", description = "The API method to create a new book")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "The book was created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PartialBookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Uncorrected response data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "409", description = "A book with the same ISBN already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))),
    })
    @PostMapping(value = "/api/v1/books", produces = "application/json", consumes = "application/json")
    ResponseEntity<PartialBookResponse> createBook(
            @Parameter(description = "The book data", required = true)
            @Valid @RequestBody
            WholeBookRequest req);
}

