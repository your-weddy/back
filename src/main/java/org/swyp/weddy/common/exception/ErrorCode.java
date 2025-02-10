package org.swyp.weddy.common.exception;

public enum ErrorCode {

    DUPLICATE_CHECKLIST("400", "사용자에게 할당된 체크리스트가 이미 존재합니다"),
    UNAUTHORIZED("401", "인증되지 않은 사용자"),
    NOT_EXISTS("404", "리소스가 존재하지 않음"),
    TOKEN_EXPIRED("4011", "JWT 토큰이 만료됨"),
    TOKEN_INVALID("402", "JWT 리프레시 토큰이 유효하지 않음")
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
