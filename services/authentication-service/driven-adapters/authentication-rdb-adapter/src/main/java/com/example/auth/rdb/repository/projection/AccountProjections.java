package com.example.auth.rdb.repository.projection;

import com.example.auth.domain.types.AccountStatus;
import lombok.Builder;

public final class AccountProjections {
    private AccountProjections() {}

    @Builder
    public record AccountAuthenticationProjection(
            String password,
            AccountStatus status
    ) {}
}
