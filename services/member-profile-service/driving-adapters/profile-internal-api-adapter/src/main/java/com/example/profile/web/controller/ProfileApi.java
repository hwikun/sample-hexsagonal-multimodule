package com.example.profile.web.controller;


import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.CreateProfileResponseDto;
import com.example.profile.web.dto.ProfileDto.DeleteProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.DeleteProfileResponseDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListResponseDto;
import com.example.profile.web.dto.ProfileDto.GetProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileResponseDto;
import com.example.profile.web.dto.ProfileDto.UpdateProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.UpdateProfileResponseDto;
import com.example.profile.web.service.ProfileProxyService;
import com.example.timer.ExeTimer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class ProfileApi {

    private final ProfileProxyService profileProxyService;

    @ExeTimer
    @PostMapping("/create")
    public CreateProfileResponseDto createProfile(@RequestBody @Valid CreateProfileRequestDto dto) {
        profileProxyService.createProfile(dto);

        return CreateProfileResponseDto.builder()
                .isSuccess(true)
                .build();
    }

    @ExeTimer
    @GetMapping("/info")
    public GetProfileResponseDto getProfile(@RequestBody @Valid GetProfileRequestDto dto) {
        return profileProxyService.getProfile(dto);
    }

    @ExeTimer
    @GetMapping("/list")
    public GetProfileListResponseDto getProfileList(@RequestBody @Valid GetProfileListRequestDto dto) {
        return profileProxyService.getProfileList(dto);
    }

    @ExeTimer
    @PostMapping("update")
    public UpdateProfileResponseDto updateProfile(@RequestBody @Valid UpdateProfileRequestDto dto) {
        profileProxyService.updateProfile(dto);
        return UpdateProfileResponseDto.builder()
                .isSuccess(true)
                .build();
    }

    @ExeTimer
    @PostMapping("delete")
    public DeleteProfileResponseDto deleteProfile(@RequestBody @Valid DeleteProfileRequestDto dto) {
        profileProxyService.deleteProfile(dto);
        return DeleteProfileResponseDto.builder()
                .isSuccess(true)
                .build();
    }
}
