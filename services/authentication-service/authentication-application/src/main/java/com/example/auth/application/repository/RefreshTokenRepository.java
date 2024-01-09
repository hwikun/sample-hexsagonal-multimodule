package com.example.auth.application.repository;

public interface RefreshTokenRepository {
    void save(String refreshToken, String username);
}
