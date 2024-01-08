package com.example.profile.domain.types;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProfileStatus {
    PENDING(false),
    ACTIVE(true),
    PROTECTED(true),
    SUSPENDED(true), // BLOCK
    SLEPT(false),
    REMOVED(false);

    private final boolean canSignIn;

    public boolean canSignIn() {
        return canSignIn;
    }
}
