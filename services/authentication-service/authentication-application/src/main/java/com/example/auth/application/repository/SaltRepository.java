package com.example.auth.application.repository;

import com.example.auth.domain.Salt;

import java.util.Optional;

public interface SaltRepository {

    Salt save(String accountId, String salt);

    Optional<Salt> findByAccountId(String accountId);
}
