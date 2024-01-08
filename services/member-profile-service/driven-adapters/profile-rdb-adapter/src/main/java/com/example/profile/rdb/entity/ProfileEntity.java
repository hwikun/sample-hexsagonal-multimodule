package com.example.profile.rdb.entity;

import com.example.core.jpa.support.UuidBaseEntity;
import com.example.profile.domain.types.Gender;
import com.example.profile.domain.types.ProfileStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "member_profile")
public class ProfileEntity extends UuidBaseEntity {
    public String accountId;
    public String username;
    public String nickname;
    public LocalDateTime birth;

    @Enumerated(EnumType.STRING)
    public Gender gender;

    @Enumerated(EnumType.STRING)
    public ProfileStatus status;

}
