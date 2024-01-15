package com.example.profile.web.service;

import com.example.profile.application.usecase.CreateProfileUseCase;
import com.example.profile.application.usecase.DeleteProfileUseCase;
import com.example.profile.application.usecase.GetProfileUseCase;
import com.example.profile.application.usecase.UpdateProfileUseCase;
import com.example.profile.domain.Profile;
import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.DeleteProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListResponseDto;
import com.example.profile.web.dto.ProfileDto.GetProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileResponseDto;
import com.example.profile.web.dto.ProfileDto.UpdateProfileRequestDto;
import com.example.profile.web.mapper.ProfileDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultProfileProxyService implements ProfileProxyService {
    private final ProfileDtoMapper mapper;
    private final CreateProfileUseCase createProfileUseCase;
    private final GetProfileUseCase getProfileUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;
    private final DeleteProfileUseCase deleteProfileUseCase;

    @Override
    public Profile createProfile(CreateProfileRequestDto dto) {
        Profile profile = mapper.toDomain(dto);
        log.debug("profile: {}", profile);
        return createProfileUseCase.createProfile(profile);
    }

    @Override
    public GetProfileResponseDto getProfile(GetProfileRequestDto dto) {
        Profile profile = getProfileUseCase.getProfile(mapper.toDomain(dto));
        return mapper.toDto(profile);
    }

    @Override
    public GetProfileListResponseDto getProfileList(GetProfileListRequestDto dto) {
        List<Profile> profileList = getProfileUseCase.getProfileList(mapper.toDomain(dto));
        return mapper.toDto(profileList);
    }

    @Override
    public void updateProfile(UpdateProfileRequestDto dto) {
        updateProfileUseCase.updateProfile(mapper.toDomain(dto));
    }

    @Override
    public void deleteProfile(DeleteProfileRequestDto dto) {
        deleteProfileUseCase.deleteProfile(mapper.toDomain(dto));
    }
}
