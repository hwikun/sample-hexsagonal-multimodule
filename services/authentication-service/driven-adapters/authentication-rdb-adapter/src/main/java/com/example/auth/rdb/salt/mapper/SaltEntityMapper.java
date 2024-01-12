package com.example.auth.rdb.salt.mapper;

import com.example.auth.domain.Salt;
import com.example.auth.rdb.salt.entity.SaltEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SaltEntityMapper {
    Salt toDomain(SaltEntity entity);
    SaltEntity toEntity(Salt salt);
}
