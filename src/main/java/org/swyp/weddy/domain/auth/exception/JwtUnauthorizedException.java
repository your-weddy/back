package org.swyp.weddy.domain.auth.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class JwtUnauthorizedException extends RuntimeException {

    private final ErrorCode errorCode;

    public JwtUnauthorizedException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
