package com.example.profile.exception;

import com.example.common.exception.CustomException;
import com.example.common.exception.ErrorCode;

public class ProfileException extends CustomException {
    public ProfileException() {
    }

    public ProfileException(String message) {
        super(message);
    }

    public ProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ProfileException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
