package com.example.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name(); // for enum
    String defaultMessage();
    HttpStatus defaultHttpStatus();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
