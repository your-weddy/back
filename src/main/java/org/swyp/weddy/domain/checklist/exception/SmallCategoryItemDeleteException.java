package org.swyp.weddy.domain.checklist.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class SmallCategoryItemDeleteException extends RuntimeException{

    private final ErrorCode errorCode;

    public SmallCategoryItemDeleteException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
