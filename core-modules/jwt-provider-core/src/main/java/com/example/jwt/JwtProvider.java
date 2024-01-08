package com.example.jwt;

import com.example.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public final class JwtProvider {
    private final Key secretKey;
    private final Long timeToLive;

    public JwtProvider(JwtProperties jwtProperties) { // 생성자 주입(우리는 @RequiredArgsConstructor로 대체하는 편.)
        this.secretKey = jwtProperties.secretKey(); // 새 방식(deprecated 된 거 말고)
        this.timeToLive = jwtProperties.ttl();
    }

    public String generateJwt(String subject, Map<String, ?> claimsMap, Instant now) {
        Claims claims = Jwts.claims(); // for Payload

        claims.setSubject(subject); // Username (아이디)
        claims.putAll(claimsMap); // 직접 넣은 나머지 모든 클레임들

        Instant expirationTime = now.plus(
                this.timeToLive,
                ChronoUnit.SECONDS
        );

        return Jwts.builder()
                .setClaims(claims) // Payload
                .setIssuedAt(Date.from(now)) // payload.iat
                .setExpiration(Date.from(expirationTime)) // payload.exp
                .signWith(secretKey, SignatureAlgorithm.HS256) // Signature, Header
                .compact();
    }
}
