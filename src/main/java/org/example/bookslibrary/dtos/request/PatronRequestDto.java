package org.example.bookslibrary.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PatronRequestDto(
        @NotBlank String name,
        @NotBlank  String email,
        @NotBlank  String phoneNumber
) {
}
