package org.example.bookslibrary.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.LibrarianLoginRequestDto;
import org.example.bookslibrary.dtos.request.LibrarianRegisterRequestDto;
import org.example.bookslibrary.services.LibrarianAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LibrarianAuthController {
    private final LibrarianAuthService adminAuthService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody LibrarianRegisterRequestDto adminRegisterRequestDto) {
        adminAuthService.registerAdmin(adminRegisterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("admin register successfully ");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LibrarianLoginRequestDto adminLoginRequestDto) {
        var response=adminAuthService.loginUser(adminLoginRequestDto);
        return ResponseEntity.ok(response);

    }
}
