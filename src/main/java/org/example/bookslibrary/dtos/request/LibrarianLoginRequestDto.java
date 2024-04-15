package org.example.bookslibrary.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LibrarianLoginRequestDto(
        @NotBlank String email,
        @NotBlank String password
) {
}
