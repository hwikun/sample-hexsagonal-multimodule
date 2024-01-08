package com.example.profile.web.service;

import com.example.profile.domain.Profile;
import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;

public interface DefaultProfileProxyService {
    Profile createProfile(CreateProfileRequestDto dto);
}
