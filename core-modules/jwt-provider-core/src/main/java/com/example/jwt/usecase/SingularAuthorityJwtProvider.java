package com.example.jwt.usecase;

public interface SingularAuthorityJwtProvider {
    String generateJwt(String username, String nickname, String authority);
}
