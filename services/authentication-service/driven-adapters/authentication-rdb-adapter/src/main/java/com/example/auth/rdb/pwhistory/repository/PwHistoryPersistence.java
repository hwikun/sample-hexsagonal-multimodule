package com.example.auth.rdb.pwhistory.repository;

import com.example.auth.application.repository.PasswordHistoryRepository;
import com.example.auth.domain.PwHistory;
import com.example.auth.rdb.pwhistory.entity.PwHistoryEntity;
import com.example.auth.rdb.pwhistory.mapper.PwHistoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class PwHistoryPersistence implements PasswordHistoryRepository {

    private final PwHistoryJpaRepository repository;
    private final PwHistoryEntityMapper pwHistoryEntityMapper;

    @Override
    public PwHistory save(String accountId, String password) {
        // 히스토리 존재 여부 확인
        List<PwHistoryEntity> entityList = repository.findAllByAccountId(accountId);
        // 유저별 히스토리 최대 100개. 넘어가면 제일 오래된 히스토리 삭제
        if (entityList.size() > 100) {
            repository.delete(entityList.get(0));
        }
        // 히스토리 저장
        PwHistoryEntity savedEntity = repository.save(PwHistoryEntity.builder()
                .accountId(accountId)
                .password(password)
                .build());
        return pwHistoryEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<PwHistory> findByPassword(String password) {
        return repository.findByPassword(password)
                .map(pwHistoryEntityMapper::toDomain);

    }
}
