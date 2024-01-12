package com.example.auth.web.service;

import com.example.auth.application.data.Tokens;
import com.example.auth.domain.Account;
import com.example.auth.domain.types.AccountStatus;
import com.example.auth.web.dto.AuthenticationDto.ChangePwRequestDto;
import com.example.auth.web.dto.AuthenticationDto.SignInRequestDto;
import com.example.auth.web.dto.AuthenticationDto.SignUpRequestDto;

import java.time.Instant;

public interface AuthenticationProxyService {
    Account signUp(SignUpRequestDto dto, AccountStatus status, Instant createdAt);

    Tokens signIn(SignInRequestDto body);

    boolean changePassword(ChangePwRequestDto dto);
}
