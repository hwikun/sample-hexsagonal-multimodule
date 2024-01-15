package com.example.profile.application.usecase;


import com.example.profile.domain.Profile;

import java.util.List;

public interface GetProfileUseCase {
    Profile getProfile(Profile profile);
    List<Profile> getProfileList(Profile profile);
}
