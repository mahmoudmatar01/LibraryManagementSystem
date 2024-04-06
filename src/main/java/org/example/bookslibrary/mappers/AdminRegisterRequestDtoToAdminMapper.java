package org.example.bookslibrary.mappers;

import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.AdminRegisterRequestDto;
import org.example.bookslibrary.entities.LibraryAdmin;
import org.example.bookslibrary.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
@RequiredArgsConstructor
public class AdminRegisterRequestDtoToAdminMapper implements Function<AdminRegisterRequestDto, LibraryAdmin> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public LibraryAdmin apply(AdminRegisterRequestDto adminRegisterRequestDto) {
        return LibraryAdmin.builder()
                .userName(adminRegisterRequestDto.userName())
                .email(adminRegisterRequestDto.email())
                .password(passwordEncoder.encode(adminRegisterRequestDto.password()))
                .userRole(Role.ROLE_ADMIN)
                .build();
    }
}
