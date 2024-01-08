package com.example.auth.redis.repository;

import com.example.auth.redis.entity.UserRefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRefreshTokenRepository extends CrudRepository<UserRefreshToken, String> {
    List<UserRefreshToken> findAllByUsername(String username);
}
