package com.example.profile.rdb.repository;

import com.example.profile.rdb.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, UUID> {
    Optional<ProfileEntity> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);

    List<ProfileEntity> findAllByNickname(String nickname);
}
