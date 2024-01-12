package com.example.auth.rdb.pwhistory.mapper;

import com.example.auth.domain.PwHistory;
import com.example.auth.rdb.pwhistory.entity.PwHistoryEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PwHistoryEntityMapper {
    PwHistory toDomain(PwHistoryEntity entity);
    PwHistoryEntity toEntity(PwHistory pwHistory);
}
