package com.example.auth.client.service;

import com.example.auth.application.repository.ProfileClientRepository;
import com.example.auth.client.dto.ProfileClientDto.CreateProfileClientRequestDto;
import com.example.auth.client.mapper.ProfileClientDtoMapper;
import com.example.auth.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileClientService implements ProfileClientRepository {
//    private final ProfileApi profileApi; 순환 참조 가능성으로 비추
//    유선 연결이 아니라 무선 통신 개념
//    api Ajax 호출로 해결(apiCall)
    private final DefaultProfileClient client;
    private final ProfileClientDtoMapper mapper;

    @Override
    public void createProfile(Account account) {
        CreateProfileClientRequestDto dto = mapper.toDto(account);
        // TODO OpenFeign Client 으로 API CALL
        client.save(dto);
    }
}
