package com.example.employeemanagement.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Configuration
public class JwtConfig {
    @Bean
    SecretKey jwtSecretKey(@Value("${app.jwt.secret-base64:}") String secretBase64, Environment environment) {
        byte[] keyBytes;
        if (secretBase64 != null && !secretBase64.isBlank()) {
            keyBytes = Base64.getDecoder().decode(secretBase64);
            if (keyBytes.length < 32) {
                throw new IllegalArgumentException("JWT_SECRET_BASE64 phai chua it nhat 32 byte");
            }
        } else {
            if (environment.acceptsProfiles(Profiles.of("prod"))) {
                throw new IllegalStateException("Can cau hinh JWT_SECRET_BASE64 khi chay prod");
            }
            keyBytes = new byte[32];
            new SecureRandom().nextBytes(keyBytes);
        }
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey key) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(key));
    }

    @Bean
    public JwtDecoder jwtDecoder(SecretKey key) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS256).build();
        decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer("employee-management"));
        return decoder;
    }
}
