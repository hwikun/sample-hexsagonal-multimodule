package com.example.auth.application.repository;

import com.example.auth.domain.Account;

public interface ProfileClientRepository {
    void createProfile(Account account);
}
