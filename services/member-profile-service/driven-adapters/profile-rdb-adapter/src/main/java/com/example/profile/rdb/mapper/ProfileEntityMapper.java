package com.example.profile.rdb.mapper;


import com.example.profile.domain.Profile;
import com.example.profile.rdb.entity.ProfileEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProfileEntityMapper {
    ProfileEntity toEntity(Profile profile);
    Profile toDomain(ProfileEntity profileEntity);
}
