package org.swyp.weddy.domain.auth.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class JwtRefreshTokenInvalidException extends RuntimeException {

    private final ErrorCode errorCode;

    public JwtRefreshTokenInvalidException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
