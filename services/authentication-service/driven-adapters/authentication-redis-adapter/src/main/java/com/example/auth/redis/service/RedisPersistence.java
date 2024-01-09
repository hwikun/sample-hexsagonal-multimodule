package com.example.auth.redis.service;

import com.example.auth.application.repository.RefreshTokenRepository;
import com.example.auth.redis.entity.UserRefreshToken;
import com.example.auth.redis.repository.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisPersistence implements RefreshTokenRepository {

    private final UserRefreshTokenRepository repository;
    @Override
    public void save(String refreshToken, String username) {
        UserRefreshToken token = UserRefreshToken.builder()
                .refreshToken(refreshToken)
                .username(username)
                .ttl(2_592_000L)
                .build();
        repository.save(token);
    }
}
