package com.example.profile.application.service;

import com.example.profile.application.repository.ProfileRepository;
import com.example.profile.application.usecase.CreateProfileUseCase;
import com.example.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public final class ProfileService implements CreateProfileUseCase {

    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

}
