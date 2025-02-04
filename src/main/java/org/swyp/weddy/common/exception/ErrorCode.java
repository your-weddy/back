package org.swyp.weddy.common.exception;

public enum ErrorCode {

    BAD_REQUEST("400", "잘못된 요청"),
    NOT_EXISTS("404", "리소스가 존재하지 않음"),
    ;

    private final String code;
    private final String reason;

    ErrorCode(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return this.code;
    }

    public String getReason() {
        return this.reason;
    }
}
