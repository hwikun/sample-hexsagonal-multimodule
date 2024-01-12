package com.example.auth.rdb.salt.repository;

import com.example.auth.application.repository.SaltRepository;
import com.example.auth.domain.Salt;
import com.example.auth.rdb.salt.entity.SaltEntity;
import com.example.auth.rdb.salt.mapper.SaltEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class SaltPersistence implements SaltRepository {

    private final SaltJpaRepository saltJpaRepository;
    private final SaltEntityMapper saltEntityMapper;
    @Override
    public Salt save(String accountId, String salt) {
        SaltEntity savedEntity = saltJpaRepository.save(SaltEntity.builder()
                .accountId(accountId)
                .salt(salt)
                .build());
        return saltEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Salt> findByAccountId(String accountId) {
        return saltJpaRepository.findByAccountId(accountId)
                .map(saltEntityMapper::toDomain);
    }
}

