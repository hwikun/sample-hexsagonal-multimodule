package com.example.auth.rdb.account.repository;

import com.example.auth.application.repository.AccountRepository;
import com.example.auth.domain.Account;
import com.example.auth.rdb.account.entity.AccountEntity;
import com.example.auth.rdb.account.mapper.AccountEntityMapper;
import com.example.auth.readmodels.AccountReadModels.AccountAuthenticationReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository // <<< JPA Repository가 아니기 때문에 애노테이션 써서 등록
@RequiredArgsConstructor
@Transactional
public class AccountPersistence implements AccountRepository { // Adapter

    private final AccountJpaRepository accountJpaRepository; // delegation
    private final AccountEntityMapper mapper;

    @Override
    public Account save(Account account) {
        // [1] 전처리
        AccountEntity entity = mapper.toEntity(account);

        // [2] 작업(위임해서 JPA Repository한테 다 시킴.)
        AccountEntity savedEntity = accountJpaRepository.save(entity);

        // [3] 후처리
        return mapper.toDomain(savedEntity); // 변환 타입: AccountEntity -> Account
    }

    @Override
    public Optional<Account> findByUsername(String username) { // Persistence 계층에서는 옵셔널.
        return accountJpaRepository
                .findSampleByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<AccountAuthenticationReadModel> findAuthenticationByUsername(String username) {
        return accountJpaRepository
                .findAuthenticationInfoByUsername(username) // Optional<Projection>
                .map(mapper::toReadModel); // Optional<ReadModel>
    }
}
