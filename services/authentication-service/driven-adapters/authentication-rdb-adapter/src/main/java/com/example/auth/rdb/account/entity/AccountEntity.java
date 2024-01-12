package com.example.auth.rdb.account.entity;

import com.example.auth.domain.types.AccountStatus;
import com.example.core.jpa.support.UuidBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "account")
public class AccountEntity extends UuidBaseEntity {
    public String username; // <<< 헥사고날이라서 진단 영역이 명확하기 때문.
    public String password;

    @Enumerated(EnumType.STRING)
    public AccountStatus status;
}