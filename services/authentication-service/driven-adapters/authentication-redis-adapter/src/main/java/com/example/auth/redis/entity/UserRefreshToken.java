package com.example.auth.redis.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import static java.util.concurrent.TimeUnit.SECONDS;



@RedisHash("user_refresh_token")
@Builder
@ToString
@EqualsAndHashCode
public class UserRefreshToken {
    @Id
    public String refreshToken;

    @Indexed
    public String username;

    @TimeToLive(unit = SECONDS)
    public long ttl;

}
