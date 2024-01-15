package com.example.profile.application.service;

import com.example.profile.application.repository.ProfileRepository;
import com.example.profile.application.usecase.CreateProfileUseCase;
import com.example.profile.application.usecase.DeleteProfileUseCase;
import com.example.profile.application.usecase.GetProfileUseCase;
import com.example.profile.application.usecase.UpdateProfileUseCase;
import com.example.profile.domain.Profile;
import com.example.profile.exception.ProfileErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public final class ProfileService implements
        CreateProfileUseCase, GetProfileUseCase, UpdateProfileUseCase, DeleteProfileUseCase {

    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(Profile profile) {
        // check Profile
        if (profileRepository.existsProfile(profile)) {
            throw ProfileErrorCode.PROFILE_CONFLICT.defaultException();
        }

        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfile(Profile profile) {
        return profileRepository.findProfile(profile)
                .orElseThrow(ProfileErrorCode.PROFILE_NULL::defaultException);
    }

    @Override
    public List<Profile> getProfileList(Profile profile) {
        return profileRepository.findProfileList(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Profile target = profileRepository.findProfile(profile)
                .orElseThrow(ProfileErrorCode.PROFILE_NULL::defaultException);
        target.nickname = profile.nickname;
        log.debug("업데이트 프로필: {}", target);
        return profileRepository.save(target);
    }

    @Override
    public boolean deleteProfile(Profile profile) {
        Profile target = profileRepository.findProfile(profile)
                .orElseThrow(ProfileErrorCode.PROFILE_NULL::defaultException);
        return profileRepository.delete(target);
    }
}
