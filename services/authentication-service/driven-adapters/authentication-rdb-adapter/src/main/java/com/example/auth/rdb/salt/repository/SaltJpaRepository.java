package com.example.auth.rdb.salt.repository;

import com.example.auth.rdb.salt.entity.SaltEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SaltJpaRepository extends JpaRepository<SaltEntity, UUID> {
    Optional<SaltEntity> findByAccountId(String accountId);
}
