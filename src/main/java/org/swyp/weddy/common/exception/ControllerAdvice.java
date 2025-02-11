package org.swyp.weddy.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.swyp.weddy.domain.checklist.exception.ChecklistAlreadyAssignedException;
import org.swyp.weddy.domain.checklist.exception.ChecklistNotExistsException;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.wiki.exception.WikiNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    protected ErrorResponse handleBusinessException(final RuntimeException exception) {
        return new ErrorResponse(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(WikiNotFoundException.class)
    protected ErrorResponse handleWikiNotFoundException(final WikiNotFoundException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(ChecklistAlreadyAssignedException.class)
    protected ErrorResponse handleChecklistAlreadyAssignedException(final ChecklistAlreadyAssignedException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(ChecklistNotExistsException.class)
    protected ErrorResponse handleChecklistNotExistsException(final ChecklistNotExistsException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(LargeCatItemNotExistsException.class)
    protected ErrorResponse handleLargeCatItemNotExistsException(final LargeCatItemNotExistsException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }



    private static class ErrorResponse {
        private final String code;
        private final String reason;

        private ErrorResponse(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.reason = errorCode.getReason();
        }

        public String getCode() {
            return this.code;
        }
        public String getReason() {
            return this.reason;
        }
    }
}
