package com.example.profile.application.repository;

import com.example.profile.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {

    Profile save(Profile profile);

    Optional<Profile> findProfile(Profile profile);

    boolean existsProfile(Profile accountId);

    boolean delete(Profile target);

    List<Profile> findProfileList(Profile profile);
}
