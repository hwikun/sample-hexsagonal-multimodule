package com.example.auth.rdb.account.repository;

import com.example.auth.rdb.account.entity.AccountEntity;
import com.example.auth.rdb.account.projection.AccountProjections.AccountAuthenticationProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findSampleByUsername(String username);
    Optional<AccountAuthenticationProjection> findAuthenticationInfoByUsername(String username);
}
