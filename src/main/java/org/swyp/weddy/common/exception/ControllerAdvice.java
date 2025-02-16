package org.swyp.weddy.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.swyp.weddy.domain.checklist.exception.ChecklistAlreadyAssignedException;
import org.swyp.weddy.domain.checklist.exception.ChecklistNotExistsException;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemAddException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemDeleteException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemNotExistsException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemUpdateException;
import org.swyp.weddy.domain.wiki.exception.WikiNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(WikiNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleWikiNotFoundException(final WikiNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(ChecklistAlreadyAssignedException.class)
    protected ResponseEntity<ErrorResponse> handleChecklistAlreadyAssignedException(final ChecklistAlreadyAssignedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(ChecklistNotExistsException.class)
    protected ResponseEntity<ErrorResponse> handleChecklistNotExistsException(final ChecklistNotExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(LargeCatItemNotExistsException.class)
    protected ResponseEntity<ErrorResponse> handleLargeCatItemNotExistsException(final LargeCatItemNotExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(SmallCategoryItemNotExistsException.class)
    protected ResponseEntity<ErrorResponse> handleSmallCategoryItemNotExistsException(final SmallCategoryItemNotExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(SmallCategoryItemAddException.class)
    protected ResponseEntity<ErrorResponse> SmallCategoryItemAddException(final SmallCategoryItemAddException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(SmallCategoryItemUpdateException.class)
    protected ResponseEntity<ErrorResponse> SmallCategoryItemUpdateException(final SmallCategoryItemUpdateException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(SmallCategoryItemDeleteException.class)
    protected ResponseEntity<ErrorResponse> SmallCategoryItemDeleteException(final SmallCategoryItemDeleteException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }
}
