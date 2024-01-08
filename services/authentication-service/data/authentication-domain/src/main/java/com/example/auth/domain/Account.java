package com.example.auth.domain;

import com.example.auth.domain.types.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account implements Serializable {
    @Getter
    private String id;
    public String username; // JPA Entity와 달리 변경돼도 DB에 직접 반영되지 X ... public 쓰고 변경 허용.
    public String password;
    public AccountStatus status;
}
