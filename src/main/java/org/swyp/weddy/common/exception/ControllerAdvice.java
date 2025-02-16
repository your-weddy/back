package org.swyp.weddy.common.exception;

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

    @ExceptionHandler(SmallCategoryItemNotExistsException.class)
    protected ErrorResponse handleSmallCategoryItemNotExistsException(final SmallCategoryItemNotExistsException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(SmallCategoryItemAddException.class)
    protected ErrorResponse SmallCategoryItemAddException(final SmallCategoryItemAddException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(SmallCategoryItemUpdateException.class)
    protected ErrorResponse SmallCategoryItemUpdateException(final SmallCategoryItemUpdateException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(SmallCategoryItemDeleteException.class)
    protected ErrorResponse SmallCategoryItemDeleteException(final SmallCategoryItemDeleteException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }
}
