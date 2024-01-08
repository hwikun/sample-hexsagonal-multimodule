package com.example.core.jpa.support;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
// No EQUALS and HASHCODE required.
public abstract class UuidBaseEntity {
    // NOTE MySQL: binary(16)
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
}
