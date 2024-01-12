package com.example.auth.rdb.account.mapper;

import com.example.auth.domain.Account;
import com.example.auth.rdb.account.entity.AccountEntity;
import com.example.auth.rdb.account.projection.AccountProjections.AccountAuthenticationProjection;
import com.example.auth.readmodels.AccountReadModels.AccountAuthenticationReadModel;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AccountEntityMapper {

//    @Mapping(target = "username", source = "userName")
//    @Mapping(target = "...", source = "...")
//    @Mapping(target = "...", source = "...")
    Account toDomain(AccountEntity entity);
    AccountEntity toEntity(Account account);
    AccountAuthenticationReadModel toReadModel(AccountAuthenticationProjection projection);
}
