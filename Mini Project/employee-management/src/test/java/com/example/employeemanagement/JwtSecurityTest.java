package com.example.employeemanagement;

import com.example.employeemanagement.config.JwtConfig;
import com.example.employeemanagement.service.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtSecurityTest {
    private final SecretKey key = new SecretKeySpec(
            "abcdefghijklmnopqrstuvwxyz123456".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    @Test
    void loginReturnsSignedJwtWithUserRole() {
        JwtConfig config = new JwtConfig();
        UserDetailsService users = name -> User.withUsername(name)
                .password(new BCryptPasswordEncoder().encode("user123"))
                .authorities(new SimpleGrantedAuthority("ROLE_USER")).build();
        JwtTokenService service = new JwtTokenService(users, new BCryptPasswordEncoder(), config.jwtEncoder(key));

        String token = service.login("student", "user123");
        Jwt jwt = config.jwtDecoder(key).decode(token);
        assertEquals("student", jwt.getSubject());
        assertEquals(List.of("ROLE_USER"), jwt.getClaimAsStringList("roles"));
        assertEquals("employee-management", jwt.getIssuer().toString());
        assertTrue(jwt.getExpiresAt().isAfter(jwt.getIssuedAt()));
    }

    @Test
    void wrongPasswordIsRejected() {
        JwtConfig config = new JwtConfig();
        UserDetailsService users = name -> User.withUsername(name)
                .password(new BCryptPasswordEncoder().encode("correct"))
                .roles("USER").build();
        JwtTokenService service = new JwtTokenService(users, new BCryptPasswordEncoder(), config.jwtEncoder(key));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.login("student", "wrong"));
        assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
    }
}
