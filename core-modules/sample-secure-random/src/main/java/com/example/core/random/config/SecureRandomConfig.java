package com.example.core.random.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
public class SecureRandomConfig {
    @Bean
    public SecureRandom secureRandom() throws NoSuchAlgorithmException {
        // follows JDK config. <<< Linux Blocking Issue
        return SecureRandom.getInstanceStrong();
    }
}
