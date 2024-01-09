package com.example.auth.application.repository;

import com.example.auth.domain.Account;
import com.example.auth.readmodels.AccountReadModels.AccountAuthenticationReadModel;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findByUsername(String username);
    Optional<AccountAuthenticationReadModel> findAuthenticationByUsername(String username);
    // ...
}
