package org.example.bookslibrary.mappers;

import org.example.bookslibrary.dtos.response.LibrarianResponseDto;
import org.example.bookslibrary.entities.Librarian;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LibrarianToLibrarianResponseDtoMapper implements Function<Librarian, LibrarianResponseDto> {
    @Override
    public LibrarianResponseDto apply(Librarian libraryAdmin) {
        return LibrarianResponseDto.builder()
                .adminId(libraryAdmin.getAdminId())
                .name(libraryAdmin.getUsername())
                .email(libraryAdmin.getEmail())
                .role(libraryAdmin.getUserRole())
                .token(libraryAdmin.getAccessToken())
                .build();
    }
}
