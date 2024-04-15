package org.example.bookslibrary.mappers;

import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.LibrarianRegisterRequestDto;
import org.example.bookslibrary.entities.Librarian;
import org.example.bookslibrary.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
@RequiredArgsConstructor
public class LibrarianRegisterRequestDtoToLibrarianMapper implements Function<LibrarianRegisterRequestDto, Librarian> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Librarian apply(LibrarianRegisterRequestDto adminRegisterRequestDto) {
        return Librarian.builder()
                .userName(adminRegisterRequestDto.userName())
                .email(adminRegisterRequestDto.email())
                .password(passwordEncoder.encode(adminRegisterRequestDto.password()))
                .userRole(Role.ROLE_ADMIN)
                .build();
    }
}
