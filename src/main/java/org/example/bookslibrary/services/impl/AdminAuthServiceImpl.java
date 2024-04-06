package org.example.bookslibrary.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.AdminLoginRequestDto;
import org.example.bookslibrary.dtos.request.AdminRegisterRequestDto;
import org.example.bookslibrary.dtos.response.AdminResponseDto;
import org.example.bookslibrary.entities.LibraryAdmin;
import org.example.bookslibrary.exceptions.BadRequestException;
import org.example.bookslibrary.mappers.AdminRegisterRequestDtoToAdminMapper;
import org.example.bookslibrary.mappers.AdminToAdminResponseDtoMapper;
import org.example.bookslibrary.repositories.AdminRepository;
import org.example.bookslibrary.security.JwtTokenUtils;
import org.example.bookslibrary.services.AdminAuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRegisterRequestDtoToAdminMapper adminRegisterRequestDtoToAdminMapper;
    private final AdminToAdminResponseDtoMapper adminToAdminResponseDtoMapper;
    private final JwtTokenUtils jwtTokenUtils;
    @Override
    public void registerAdmin(AdminRegisterRequestDto registerRequest) {
        if (adminRepository.existsByEmail(registerRequest.email())) {
            throw new BadRequestException("Email address already exists.");
        }
        LibraryAdmin admin=adminRegisterRequestDtoToAdminMapper.apply(registerRequest);
        adminRepository.save(admin);
    }

    @Override
    public AdminResponseDto loginUser(AdminLoginRequestDto loginRequest) {
        LibraryAdmin admin = adminRepository.findByEmail(loginRequest.email()).orElseThrow(
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
