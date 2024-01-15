package com.example.profile.domain;

import com.example.profile.domain.types.Gender;
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
public class Profile implements Serializable {
    @Getter
    private String id;
    public String accountId;
    public String username;
    public String birth;
    public Gender gender;
    public String nickname;
}