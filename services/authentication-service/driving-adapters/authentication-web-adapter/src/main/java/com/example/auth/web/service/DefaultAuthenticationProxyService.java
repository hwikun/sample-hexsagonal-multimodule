package com.example.auth.web.service;

import com.example.auth.application.data.Tokens;
import com.example.auth.application.usecase.ChangePwUseCase;
import com.example.auth.application.usecase.SignInUseCase;
import com.example.auth.application.usecase.SignUpUseCase;
import com.example.auth.domain.Account;
import com.example.auth.domain.types.AccountStatus;
import com.example.auth.web.dto.AuthenticationDto.ChangePwRequestDto;
import com.example.auth.web.dto.AuthenticationDto.SignInRequestDto;
import com.example.auth.web.dto.AuthenticationDto.SignUpRequestDto;
import com.example.auth.web.mapper.AccountDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public final class DefaultAuthenticationProxyService implements AuthenticationProxyService {

    private final SignUpUseCase signUpUseCase;
    private final SignInUseCase signInUseCase;
    private final AccountDtoMapper mapper;
    private final ChangePwUseCase changePwUseCase;

    @Override
    public Account signUp(SignUpRequestDto dto, AccountStatus status, Instant createdAt) {
        Account account = mapper.toDomain(dto, status, createdAt);
        return signUpUseCase.signUp(account);
    }

    @Override
    public Tokens signIn(SignInRequestDto body) {
        Tokens tokens = signInUseCase.signIn(body.username(), body.rawPassword());
        // TODO 엑세스 토큰은 반환하고 리프레시 토큰은 쿠키에 담기
        // TODO 반환은 도메인으로, DTO는 컨트롤러 재량에 맡기기
//        response.addCookie(tokens.refreshToken());
        return tokens;
    }

    @Override
    public boolean changePassword(ChangePwRequestDto dto) {
        if (!dto.newPassword().equals(dto.confirmPassword())) {
            return false;
        }
        return changePwUseCase.changePassword(dto.username(), dto.newPassword());
    }

}
