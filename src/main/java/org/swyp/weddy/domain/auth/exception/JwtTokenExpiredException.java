package org.swyp.weddy.domain.auth.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class JwtTokenExpiredException extends RuntimeException {

    private final ErrorCode errorCode;

    public JwtTokenExpiredException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
