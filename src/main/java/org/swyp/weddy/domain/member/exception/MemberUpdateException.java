package org.swyp.weddy.domain.member.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class MemberUpdateException extends RuntimeException{

    private final ErrorCode errorCode;

    public MemberUpdateException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
