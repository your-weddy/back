package org.swyp.weddy.common.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

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

    public HttpStatusCode getHttpStatusCode() {
        Integer code = Integer.valueOf(this.getCode());
        return HttpStatusCode.valueOf(code);
    }

    public ResponseEntity<ErrorResponse> makeResponseEntity(HttpStatusCode httpStatusCode) {
        return ResponseEntity.status(httpStatusCode).body(this);
    }
}
