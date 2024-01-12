package com.example.auth.web.controller;

import com.example.auth.application.data.Tokens;
import com.example.auth.domain.types.AccountStatus;
import com.example.auth.web.dto.AuthenticationDto.ChangePwRequestDto;
import com.example.auth.web.dto.AuthenticationDto.ChangePwResponseDto;
import com.example.auth.web.dto.AuthenticationDto.SignInRequestDto;
import com.example.auth.web.dto.AuthenticationDto.SignInResponseDto;
import com.example.auth.web.dto.AuthenticationDto.SignUpRequestDto;
import com.example.auth.web.dto.AuthenticationDto.SignUpResponseDto;
import com.example.auth.web.service.AuthenticationProxyService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/auth")
public final class AuthenticationApi {

    private final AuthenticationProxyService authenticationProxyService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public SignUpResponseDto signUp(@RequestBody @Valid SignUpRequestDto body) {
        Instant now = Instant.now();
        authenticationProxyService.signUp(body, AccountStatus.ACTIVE, now);

        return SignUpResponseDto.builder()
                .success(true)
                .build();
    }

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(
            @RequestBody @Valid SignInRequestDto body,
            HttpServletResponse response
    ) {
        log.debug("body: {}", body);
        Tokens tokens = authenticationProxyService.signIn(body);

        // TODO 특정 빈에 담는것 추천
        // HTTP Only 쿠키에 담기
        Cookie refreshTokenCookie = new Cookie("refresh_token", tokens.refreshToken());
        refreshTokenCookie.setDomain("");
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);
//        refreshTokenCookie.setSecure(true); // https 전용
        refreshTokenCookie.setMaxAge(2_592_000);
        response.addCookie(refreshTokenCookie);

        return SignInResponseDto.builder()
                .accessToken(tokens.accessToken())
                .build();
    }

    @PostMapping("/change-password")
    public ChangePwResponseDto changePassword(@RequestBody @Valid ChangePwRequestDto dto) {
        boolean isSuccess = authenticationProxyService.changePassword(dto);

        return ChangePwResponseDto.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
