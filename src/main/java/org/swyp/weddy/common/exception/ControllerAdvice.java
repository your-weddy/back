package org.swyp.weddy.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.swyp.weddy.domain.wiki.exception.WikiNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    protected ErrorResponse handleBusinessException(final WikiNotFoundException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    private static class ErrorResponse {
        private final Integer code;
        private final String reason;

        private ErrorResponse(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.reason = errorCode.getReason();
        }

        public Integer getCode() {
            return this.code;
        }
        public String getReason() {
            return this.reason;
        }
    }
}
