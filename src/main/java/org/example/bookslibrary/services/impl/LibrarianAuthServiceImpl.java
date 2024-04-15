package org.example.bookslibrary.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.LibrarianLoginRequestDto;
import org.example.bookslibrary.dtos.request.LibrarianRegisterRequestDto;
import org.example.bookslibrary.dtos.response.LibrarianResponseDto;
import org.example.bookslibrary.entities.Librarian;
import org.example.bookslibrary.exceptions.BadRequestException;
import org.example.bookslibrary.mappers.LibrarianRegisterRequestDtoToLibrarianMapper;
import org.example.bookslibrary.mappers.LibrarianToLibrarianResponseDtoMapper;
import org.example.bookslibrary.repositories.LibrarianRepository;
import org.example.bookslibrary.security.JwtTokenUtils;
import org.example.bookslibrary.services.LibrarianAuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianAuthServiceImpl implements LibrarianAuthService {
    private final LibrarianRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final LibrarianRegisterRequestDtoToLibrarianMapper adminRegisterRequestDtoToAdminMapper;
    private final LibrarianToLibrarianResponseDtoMapper adminToAdminResponseDtoMapper;
    private final JwtTokenUtils jwtTokenUtils;
    @Override
    public void registerAdmin(LibrarianRegisterRequestDto registerRequest) {
        if (adminRepository.existsByEmail(registerRequest.email())) {
            throw new BadRequestException("Email address already exists.");
        }
        Librarian admin=adminRegisterRequestDtoToAdminMapper.apply(registerRequest);
        adminRepository.save(admin);
    }

    @Override
    public LibrarianResponseDto loginUser(LibrarianLoginRequestDto loginRequest) {
        Librarian admin = adminRepository.findByEmail(loginRequest.email()).orElseThrow(
                ()->new BadRequestException("Admin with email not founded!")
        );
        checkPasswordsMatch(loginRequest.password(), admin.getPassword());
        String jwtToken = jwtTokenUtils.generateToken(admin);
        admin.setAccessToken(jwtToken);
        admin=adminRepository.save(admin);
        return adminToAdminResponseDtoMapper.apply(admin);
    }

    // Helper Method
    private void checkPasswordsMatch(String pass1,String pass2){
        if (!passwordEncoder.matches(pass1, pass2)) {
            throw new BadRequestException("Invalid password");
        }
    }
}
