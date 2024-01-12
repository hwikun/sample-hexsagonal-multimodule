package com.example.auth.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public final class AuthenticationDto {
    private AuthenticationDto() {}

    @Builder
    public record SignUpRequestDto(
            @NotBlank
            String username,

            @NotBlank
            String password
    ) {}

    @Builder
    public record SignInRequestDto(
            @NotBlank
            String username,
            @NotBlank
            @JsonProperty("password")
            String rawPassword
    ) {}

    @Builder
    public record SignUpResponseDto(
            @JsonInclude(Include.NON_DEFAULT) // null, false일 때는 생략.
            Boolean success
    ) {}

    // { "success": true } or { } ->        result.success // undefined
    // undefined

    @Builder
    public record SignInResponseDto(
            @JsonProperty("access_token")
            @JsonInclude(Include.NON_EMPTY)
            String accessToken,

            @JsonProperty("refresh_token")
            @JsonInclude(Include.NON_EMPTY)
            String refreshToken


    ) {}

    @Builder
    public record ChangePwRequestDto(
            @NotBlank
            String username,
            @NotBlank
            String newPassword,

            @NotBlank
            String confirmPassword
    ) {}

    @Builder
    public record ChangePwResponseDto(

            boolean isSuccess
    ) {}
}
