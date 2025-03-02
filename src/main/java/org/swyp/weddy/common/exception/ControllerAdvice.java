package org.swyp.weddy.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.swyp.weddy.domain.auth.exception.JwtRefreshTokenInvalidException;
import org.swyp.weddy.domain.auth.exception.JwtTokenExpiredException;
import org.swyp.weddy.domain.auth.exception.JwtUnauthorizedException;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.checklist.exception.*;
import org.swyp.weddy.domain.member.exception.MemberUpdateException;
import org.swyp.weddy.domain.storage.exception.FileDeleteException;
import org.swyp.weddy.domain.storage.exception.FileUploadException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(JwtRefreshTokenInvalidException.class)
    protected ResponseEntity<ErrorResponse> jwtRefreshTokenInvalidException(final JwtRefreshTokenInvalidException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(JwtTokenExpiredException.class)
    protected ResponseEntity<ErrorResponse> jwtTokenExpiredException(final JwtTokenExpiredException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(JwtUnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> jwtUnauthorizedException(final JwtUnauthorizedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<ErrorResponse> userNotFoundException(final MemberNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(MemberUpdateException.class)
    protected ResponseEntity<ErrorResponse> memberUpdateException(final MemberUpdateException exception) {
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

    @ExceptionHandler(LargeCatItemUpdateException.class)
    protected ResponseEntity<ErrorResponse> LargeCatItemUpdateException(final LargeCatItemNotExistsException exception) {
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

    @ExceptionHandler(FileUploadException.class)
    protected ResponseEntity<ErrorResponse> FileUploadException(final FileUploadException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }

    @ExceptionHandler(FileDeleteException.class)
    protected ResponseEntity<ErrorResponse> FileDeleteException(final FileDeleteException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return errorResponse.makeResponseEntity();
    }
}
