package org.example.bookslibrary.services;


import org.example.bookslibrary.dtos.request.AdminLoginRequestDto;
import org.example.bookslibrary.dtos.request.AdminRegisterRequestDto;
import org.example.bookslibrary.dtos.response.AdminResponseDto;

public interface AdminAuthService {
    void registerAdmin(AdminRegisterRequestDto registerRequest);
    AdminResponseDto loginUser(AdminLoginRequestDto loginRequest);
}
