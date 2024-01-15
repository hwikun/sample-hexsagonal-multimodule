package com.example.profile.web.mapper;

import com.example.profile.domain.Profile;
import com.example.profile.web.dto.ProfileDto.CreateProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.DeleteProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileListResponseDto;
import com.example.profile.web.dto.ProfileDto.GetProfileRequestDto;
import com.example.profile.web.dto.ProfileDto.GetProfileResponseDto;
import com.example.profile.web.dto.ProfileDto.UpdateProfileRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProfileDtoMapper {

    Profile toDomain(CreateProfileRequestDto dto);

    Profile toDomain(GetProfileRequestDto dto);

    Profile toDomain(GetProfileListRequestDto dto);

    GetProfileResponseDto toDto(Profile profile);

    GetProfileListResponseDto toDto(List<Profile> profileList);

    Profile toDomain(UpdateProfileRequestDto dto);

    Profile toDomain(DeleteProfileRequestDto dto);
}
