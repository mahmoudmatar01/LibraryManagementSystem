package org.example.bookslibrary.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AdminRegisterRequestDto(
       @NotBlank String userName,
       @NotBlank String email,
       @NotBlank String password
) {
}
