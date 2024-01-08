package com.example.profile.web.controller;

import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.CreateProfileResponseDto;
import com.example.profile.web.service.DefaultProfileProxyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/profile")
public final class ProfileApi {

    private final DefaultProfileProxyService profileProxyService;

    @PostMapping("create")
    public CreateProfileResponseDto createProfile(@RequestBody @Valid CreateProfileRequestDto dto) {
        profileProxyService.createProfile(dto);

        return CreateProfileResponseDto.builder()
                .isSuccess(true)
                .build();
    };
}
