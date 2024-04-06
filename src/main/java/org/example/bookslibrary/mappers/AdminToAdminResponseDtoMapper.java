package org.example.bookslibrary.mappers;

import org.example.bookslibrary.dtos.response.AdminResponseDto;
import org.example.bookslibrary.entities.LibraryAdmin;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AdminToAdminResponseDtoMapper implements Function<LibraryAdmin, AdminResponseDto> {
    @Override
    public AdminResponseDto apply(LibraryAdmin libraryAdmin) {
        return AdminResponseDto.builder()
                .adminId(libraryAdmin.getAdminId())
                .name(libraryAdmin.getUsername())
                .email(libraryAdmin.getEmail())
                .role(libraryAdmin.getUserRole())
                .token(libraryAdmin.getAccessToken())
                .build();
    }
}
