package org.swyp.weddy.common.exception;

class ErrorResponse {
    private final String code;
    private final String reason;

    ErrorResponse(ErrorCode errorCode) {
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
