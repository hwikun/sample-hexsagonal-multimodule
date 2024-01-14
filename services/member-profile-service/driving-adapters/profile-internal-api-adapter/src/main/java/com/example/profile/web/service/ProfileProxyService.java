package com.example.profile.web.service;

import com.example.profile.application.usecase.CreateProfileUseCase;
import com.example.profile.domain.Profile;
import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import com.example.profile.web.mapper.ProfileDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileProxyService implements DefaultProfileProxyService {

    private final ProfileDtoMapper mapper;
    private final CreateProfileUseCase createProfileUseCase;


    @Override
    public Profile createProfile(CreateProfileRequestDto dto) {
        Profile profile = mapper.toDomain(dto);
        log.debug("profile: {}", profile);
        return createProfileUseCase.createProfile(profile);
    }
}
