package com.example.profile.rdb.repository;

import com.example.profile.application.repository.ProfileRepository;
import com.example.profile.domain.Profile;
import com.example.profile.rdb.entity.ProfileEntity;
import com.example.profile.rdb.mapper.ProfileEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfilePersistence implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;
    private final ProfileEntityMapper mapper;

    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = mapper.toEntity(profile);
        ProfileEntity savedEntity = profileJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
