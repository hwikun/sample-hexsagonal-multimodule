package com.example.auth.rdb.account.projection;

import com.example.auth.domain.types.AccountStatus;
import lombok.Builder;

// JPA에서 원하는 컬럼만 가져오고 싶을 때 사용하는 Projection;
// Entity 개념.(비슷한)
public final class AccountProjections {
    private AccountProjections() {}

    // record 로 구현하면 편함
    @Builder
    public record AccountAuthenticationProjection(
            String password,
            AccountStatus status
    ) {}
}
