package com.example.employeemanagement.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtTokenService {
    private final UserDetailsService users;
    private final PasswordEncoder passwords;
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(UserDetailsService users, PasswordEncoder passwords, JwtEncoder jwtEncoder) {
        this.users = users;
        this.passwords = passwords;
        this.jwtEncoder = jwtEncoder;
    }

    public String login(String username, String password) {
        UserDetails user;
        try {
            user = users.loadUserByUsername(username);
        } catch (UsernameNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sai tai khoan hoac mat khau");
        }
        if (!user.isEnabled() || !passwords.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sai tai khoan hoac mat khau");
        }
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("employee-management")
                .subject(user.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .claim("roles", user.getAuthorities().stream().map(a -> a.getAuthority()).toList())
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
