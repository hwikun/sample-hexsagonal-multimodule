package com.example.profile.web.mapper;

import com.example.profile.domain.Profile;
import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import org.mapstruct.Mapper;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProfileDtoMapper {

    Profile toDomain(CreateProfileRequestDto dto);
}
