package com.example.auth.rdb.pwhistory.entity;

import com.example.core.jpa.support.UuidBaseEntity;
import jakarta.persistence.Entity;
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
@Table(name = "password_history")
public class PwHistoryEntity extends UuidBaseEntity {
    public String accountId;
    public String password;
}
