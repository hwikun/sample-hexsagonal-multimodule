package com.example.auth.application.data;

import lombok.Builder;

@Builder
public record Tokens(
        String accessToken,
        String refreshToken
) {
}
