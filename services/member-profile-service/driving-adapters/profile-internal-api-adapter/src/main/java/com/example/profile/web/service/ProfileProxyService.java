package com.example.profile.web.service;

import com.example.profile.domain.Profile;
import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.DeleteProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListResponseDto;
import com.example.profile.web.dto.ProfileDto.GetProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileResponseDto;
import com.example.profile.web.dto.ProfileDto.UpdateProfileRequestDto;

public interface ProfileProxyService {
    Profile createProfile(CreateProfileRequestDto dto);

    GetProfileResponseDto getProfile(GetProfileRequestDto dto);

    GetProfileListResponseDto getProfileList(GetProfileListRequestDto dto);

    void updateProfile(UpdateProfileRequestDto dto);

    void deleteProfile(DeleteProfileRequestDto dto);
}
