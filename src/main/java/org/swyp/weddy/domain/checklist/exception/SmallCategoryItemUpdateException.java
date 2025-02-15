package org.swyp.weddy.domain.checklist.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class SmallCategoryItemUpdateException extends RuntimeException{

    private final ErrorCode errorCode;

    public SmallCategoryItemUpdateException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
