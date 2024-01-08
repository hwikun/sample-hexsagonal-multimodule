package com.example.auth.readmodels;

import com.example.auth.domain.types.AccountStatus;
import lombok.Builder;

public final class AccountReadModels {
    private AccountReadModels() {}

    @Builder
    public record AccountListViewReadModel(
            // ...
    ) {}

    @Builder
    public record AccountDetailedViewReadModel(
            String id,
            String username,
            String password,
            AccountStatus status
    ) {}

    @Builder
    public record AccountAuthenticationReadModel(
            String password,
            AccountStatus status
    ) {}
}
