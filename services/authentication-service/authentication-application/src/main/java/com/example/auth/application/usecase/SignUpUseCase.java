package com.example.auth.application.usecase;

import com.example.auth.domain.Account;

public interface SignUpUseCase {
    //
    Account signUp(Account account);
    // Account signUp(SignUpRequestDtoCaseA dto, ... ); // 확장성 X, 편의성 O
    // Account signUp(SignUpRequestDtoCaseB dto, ... ); // 확장성 X, 편의성 O
    // Account signUp(SignUpRequestDtoCaseC dto, ... ); // 확장성 X, 편의성 O
}
