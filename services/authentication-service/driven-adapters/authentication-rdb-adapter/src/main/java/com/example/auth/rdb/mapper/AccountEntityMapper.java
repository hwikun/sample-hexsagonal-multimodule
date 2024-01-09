package com.example.auth.rdb.mapper;

import com.example.auth.domain.Account;
import com.example.auth.rdb.entity.AccountEntity;
import com.example.auth.rdb.repository.projection.AccountProjections.AccountAuthenticationProjection;
import com.example.auth.readmodels.AccountReadModels.AccountAuthenticationReadModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
