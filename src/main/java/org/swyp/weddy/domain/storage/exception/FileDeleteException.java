package org.swyp.weddy.domain.storage.exception;

import org.swyp.weddy.common.exception.ErrorCode;

public class FileDeleteException extends RuntimeException{

    private final ErrorCode errorCode;

    public FileDeleteException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
