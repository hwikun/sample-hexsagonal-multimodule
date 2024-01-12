package com.example.auth.domain;

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
public class PwHistory implements Serializable {
    @Getter
    private String id;
    public String accountId;
    public String password;
}
