package com.example.employeemanagement.controller;

import com.example.employeemanagement.service.JwtTokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthController {
    private final JwtTokenService tokens;

    public JwtAuthController(JwtTokenService tokens) {
        this.tokens = tokens;
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return new TokenResponse(tokens.login(request.username(), request.password()), "Bearer", 3600);
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    public record TokenResponse(String accessToken, String tokenType, long expiresIn) {}
}
