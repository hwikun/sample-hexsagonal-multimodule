package com.example.jwt.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

import java.security.Key;

@ConfigurationProperties("app.security.jwt")
@ConfigurationPropertiesBinding
public record JwtProperties(
        String secret,
        Long ttl
) {
    public JwtProperties { // 컴팩트 생성자 (파라미터들 확인, 변경, ...)
        if (ttl == null) {
            ttl = 1_800L; // [sec] (600당 10분)
        }
    }

    public Key secretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
