package com.example.profile.rdb.repository;

import com.example.profile.application.repository.ProfileRepository;
import com.example.profile.domain.Profile;
import com.example.profile.rdb.entity.ProfileEntity;
import com.example.profile.rdb.mapper.ProfileEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProfilePersistence implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;
    private final ProfileEntityMapper mapper;

    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = mapper.toEntity(profile);
        ProfileEntity savedEntity = profileJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Profile> findProfile(Profile profile) {
        return profileJpaRepository.findByAccountId(profile.accountId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsProfile(Profile profile) {
        return profileJpaRepository.existsByAccountId(profile.accountId);
    }

    @Override
    public boolean delete(Profile profile) {
        if(profileJpaRepository.existsByAccountId(profile.accountId)) {
            profileJpaRepository.delete(mapper.toEntity(profile));
            return true;
        }
        return false;
    }

    @Override
    public List<Profile> findProfileList(Profile profile) {
        List<ProfileEntity> entityList = profileJpaRepository.findAllByNickname(profile.nickname);
        return entityList.stream().map(mapper::toDomain).toList();
    }
}
