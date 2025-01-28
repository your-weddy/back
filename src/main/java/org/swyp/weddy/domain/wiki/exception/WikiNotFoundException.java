package org.swyp.weddy.domain.wiki.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class WikiNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public WikiNotFoundException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
