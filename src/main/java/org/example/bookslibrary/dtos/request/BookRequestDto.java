package org.example.bookslibrary.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record BookRequestDto(
         @NotBlank String title,
         @NotBlank String author,
         int publicationYear,
         @NotBlank String isbn
) {
}
