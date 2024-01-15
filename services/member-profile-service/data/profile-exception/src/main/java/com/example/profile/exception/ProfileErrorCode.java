package com.example.profile.exception;

import com.example.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ProfileErrorCode implements ErrorCode {
    PROFILE_NULL("프로필이 없습니다.", HttpStatus.NOT_FOUND),
    DEFAULT("알 수 없는 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    PROFILE_CONFLICT("중복된 프로파일입니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;
    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public RuntimeException defaultException() {
        return new ProfileException(this);
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return new ProfileException(this, cause);
    }
}
