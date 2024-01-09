package com.example.auth.web.mapper;

import com.example.auth.application.data.Tokens;
import com.example.auth.domain.Account;
import com.example.auth.domain.types.AccountStatus;
import com.example.auth.web.dto.AuthenticationDto.SignInResponseDto;
import com.example.auth.web.dto.AuthenticationDto.SignUpRequestDto;
import org.mapstruct.Mapper;

import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AccountDtoMapper {

    Account toDomain(SignUpRequestDto dto, AccountStatus status, Instant createdAt);

    SignInResponseDto toDto(Tokens tokens);
}
