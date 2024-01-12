package com.example.auth.rdb.salt.entity;

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
@Table(name = "salt")
public class SaltEntity extends UuidBaseEntity {
    public String accountId;
    public String salt;
}
