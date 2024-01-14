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
    ) {

    }
}
