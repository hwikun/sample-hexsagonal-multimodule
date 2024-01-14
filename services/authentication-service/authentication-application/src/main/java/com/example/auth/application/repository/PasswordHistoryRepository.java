package com.example.auth.application.repository;

import com.example.auth.domain.PwHistory;

import java.util.Optional;

public interface PasswordHistoryRepository {
    PwHistory save(String accountId, String password);

    Optional<PwHistory> findByPassword(String password);

    int countAllByPassword(String password);

    boolean existsByPassword(String password);
}
