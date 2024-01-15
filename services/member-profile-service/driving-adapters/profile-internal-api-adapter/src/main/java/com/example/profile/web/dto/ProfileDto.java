package com.example.profile.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public final class ProfileDto {
    private ProfileDto() {}

    @Builder
    public record CreateProfileRequestDto(
            @NotBlank
            String accountId,
            @NotBlank
            String username
    ) {}

    @Builder
    public record CreateProfileResponseDto(
            boolean isSuccess
    ) {}

    @Builder
    public record GetProfileRequestDto(

    ) {}

    @Builder
    public record GetProfileResponseDto(
        String id,
        String accountId,
        String username,
        String gender,
        String birth,
        String nickname
    ) {}

    @Builder
    public record GetProfileListRequestDto(

    ) {}

    @Builder
    public record GetProfileListResponseDto(

    ) {}

    @Builder
    public record UpdateProfileResponseDto(
        boolean isSuccess
    ) {}

    @Builder
    public record UpdateProfileRequestDto(

    ) {}

    @Builder
    public record DeleteProfileRequestDto(

    ) {}

    @Builder
    public record DeleteProfileResponseDto(
        boolean isSuccess
    ) {}
}
