package com.example.core.random.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public final class DefaultSecuredRandom implements SecuredRandom {
//    private final Encoder encoder;
    private final SecureRandom secureRandom;


    private final static char[] charSet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Override
    public String generate(int length) {
        StringBuilder builder = new StringBuilder(length); // TODO check trigger to increase capacity

        for (int i  = 0; i < length; i++) {
            builder.append(charSet[secureRandom.nextInt(charSet.length)]);
        }

        return builder.toString();
    }
}
