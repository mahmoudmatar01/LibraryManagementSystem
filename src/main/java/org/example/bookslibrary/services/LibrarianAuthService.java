package org.example.bookslibrary.services;


import org.example.bookslibrary.dtos.request.LibrarianLoginRequestDto;
import org.example.bookslibrary.dtos.request.LibrarianRegisterRequestDto;
import org.example.bookslibrary.dtos.response.LibrarianResponseDto;

public interface LibrarianAuthService {
    void registerAdmin(LibrarianRegisterRequestDto registerRequest);
    LibrarianResponseDto loginUser(LibrarianLoginRequestDto loginRequest);
}
