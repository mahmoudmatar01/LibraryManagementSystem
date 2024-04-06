package org.example.bookslibrary.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.AdminLoginRequestDto;
import org.example.bookslibrary.dtos.request.AdminRegisterRequestDto;
import org.example.bookslibrary.services.AdminAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminAuthService adminAuthService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AdminRegisterRequestDto adminRegisterRequestDto) {
        adminAuthService.registerAdmin(adminRegisterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("admin register successfully ");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        var response=adminAuthService.loginUser(adminLoginRequestDto);
        return ResponseEntity.ok(response);

    }
}
