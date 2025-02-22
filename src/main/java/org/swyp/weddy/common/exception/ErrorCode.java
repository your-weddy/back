package org.swyp.weddy.common.exception;

public enum ErrorCode {

    BAD_REQUEST        (400, "잘못된 요청"),
    DUPLICATE_CHECKLIST(400, "사용자에게 할당된 체크리스트가 이미 존재합니다"),
    UNAUTHORIZED       (401, "인증되지 않은 사용자"),
    NOT_EXISTS         (404, "리소스가 존재하지 않음"),
    TOKEN_EXPIRED      (4011, "JWT 토큰이 만료됨"),
    TOKEN_INVALID      (402, "JWT 리프레시 토큰이 유효하지 않음"),
    DELETE_FAILED      (500, "소분류 항목 삭제에 실패하였습니다"),
    UPDATE_FAILED      (500, "소분류 항목 변경에 실패하였습니다"),
    ADD_FAILED         (500, "소분류 항목 추가에 실패하였습니다"),

            ;

    private final Integer code;
    private final String reason;

    ErrorCode(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getReason() {
        return this.reason;
    }
}
