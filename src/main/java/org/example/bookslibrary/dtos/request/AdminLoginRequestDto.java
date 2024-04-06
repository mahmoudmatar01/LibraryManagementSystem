package org.example.bookslibrary.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AdminLoginRequestDto(
        @NotBlank String email,
        @NotBlank String password
) {
}
