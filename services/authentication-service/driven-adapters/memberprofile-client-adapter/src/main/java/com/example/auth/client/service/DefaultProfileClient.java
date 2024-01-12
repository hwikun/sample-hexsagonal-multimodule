package com.example.auth.client.service;

import com.example.auth.client.dto.ProfileClientDto.CreateProfileClientRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "member-profile", url = "http://localhost:8081")
public interface DefaultProfileClient {
    // TODO API 스펙에 맞게 함수 생성
    // repository처럼 만들기
    // Param인지 Body 인지 잘 보고 DTO생성 여부 결정
    // 보안 생각하기

    @PostMapping(value = "api/profile/create")
    void save(@RequestBody CreateProfileClientRequestDto dto);
}
