package com.example.cors.properties.types;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@RequiredArgsConstructor
public enum LogLevel {
    ALL(LoggerFactory.getLogger(LogLevel.class)::trace, LoggerFactory.getLogger(LogLevel.class)::trace),
    TRACE(LoggerFactory.getLogger(LogLevel.class)::trace, LoggerFactory.getLogger(LogLevel.class)::trace),
    DEBUG(LoggerFactory.getLogger(LogLevel.class)::debug, LoggerFactory.getLogger(LogLevel.class)::debug),
    INFO(LoggerFactory.getLogger(LogLevel.class)::info, LoggerFactory.getLogger(LogLevel.class)::info),
    WARN(LoggerFactory.getLogger(LogLevel.class)::warn, LoggerFactory.getLogger(LogLevel.class)::warn),
    ERROR(LoggerFactory.getLogger(LogLevel.class)::error, LoggerFactory.getLogger(LogLevel.class)::error),
    OFF((message) -> {}, (message, args) -> {});

    private final Consumer<String> logConsumer;
    private final BiConsumer<String, Object[]> logBiConsumer;

    public void log(String message) {
        this.logConsumer.accept(message);
    }

    public void log(String message, Object... args) {
        this.logBiConsumer.accept(message, args);
    }
}