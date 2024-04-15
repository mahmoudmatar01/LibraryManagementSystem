package org.example.bookslibrary.dtos.response;

import lombok.Builder;
import org.example.bookslibrary.enums.Role;

@Builder
public record LibrarianResponseDto(
        Long adminId,
        String name,
        String email,
        Role role,
        String token
) {
}
