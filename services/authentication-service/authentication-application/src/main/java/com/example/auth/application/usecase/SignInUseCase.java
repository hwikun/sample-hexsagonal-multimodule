package com.example.auth.application.usecase;

import com.example.auth.application.data.Tokens;

public interface SignInUseCase {
    Tokens signIn(String username, String rawPassword);
}
