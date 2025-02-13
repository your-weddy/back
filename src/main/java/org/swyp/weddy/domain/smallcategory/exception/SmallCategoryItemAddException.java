package org.swyp.weddy.domain.smallcategory.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class SmallCategoryItemAddException extends RuntimeException{

    private final ErrorCode errorCode;

    public SmallCategoryItemAddException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
