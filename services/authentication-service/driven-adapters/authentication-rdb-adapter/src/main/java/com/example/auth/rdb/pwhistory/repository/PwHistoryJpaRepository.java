package com.example.auth.rdb.pwhistory.repository;

import com.example.auth.rdb.pwhistory.entity.PwHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PwHistoryJpaRepository extends JpaRepository<PwHistoryEntity, UUID> {
    Optional<PwHistoryEntity> findByAccountId(String accountId);
    List<PwHistoryEntity> findAllByAccountId(String accountId);
    Optional<PwHistoryEntity> findByPassword(String password);
    int countAllByPassword(String password);

    boolean existsByPassword(String password);

    int countByAccountId(String accountId);
}
