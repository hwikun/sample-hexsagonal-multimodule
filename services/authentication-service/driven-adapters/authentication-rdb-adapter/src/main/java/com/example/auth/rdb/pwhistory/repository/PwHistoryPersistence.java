package com.example.auth.rdb.pwhistory.repository;

import com.example.auth.application.repository.PasswordHistoryRepository;
import com.example.auth.domain.PwHistory;
import com.example.auth.rdb.pwhistory.entity.PwHistoryEntity;
import com.example.auth.rdb.pwhistory.mapper.PwHistoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class PwHistoryPersistence implements PasswordHistoryRepository {

    private final PwHistoryJpaRepository pwHistoryJpaRepository;
    private final PwHistoryEntityMapper pwHistoryEntityMapper;

    // save면 save만 구현. 나머지 제약 사항은 service에서.
    @Override
    public PwHistory save(String accountId, String password) {
        PwHistoryEntity savedEntity = pwHistoryJpaRepository.save(PwHistoryEntity.builder()
                .accountId(accountId)
                .password(password)
                .build());
        return pwHistoryEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<PwHistory> findByPassword(String password) {
        return pwHistoryJpaRepository.findByPassword(password)
                .map(pwHistoryEntityMapper::toDomain);
    }

    @Override
    public int countAllByPassword(String password) {
        return pwHistoryJpaRepository.countAllByPassword(password);
    }

    @Override
    public boolean existsByPassword(String password) {
        return pwHistoryJpaRepository.existsByPassword(password);
    }
}
