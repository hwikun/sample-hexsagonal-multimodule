package com.example.auth.client.mapper;

import com.example.auth.client.dto.ProfileClientDto.CreateProfileClientRequestDto;
import com.example.auth.domain.Account;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProfileClientDtoMapper {

    CreateProfileClientRequestDto toDto(Account account);
}
