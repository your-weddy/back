package org.swyp.weddy.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Slf4j
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

    public HttpStatusCode convertHttpStatusCode() {
        Integer code = Integer.valueOf(this.getCode());
        try {
            return HttpStatusCode.valueOf(code);
        } catch (IllegalArgumentException e) {
            log.error(
                    "Cannot convert code into HttpStatusCode: {}. ErrorCode.code should be between 100 <= code <= 999",
                    this.getCode(),
                    e
            );
            throw new IllegalArgumentException(
                    "Cannot convert code into HttpStatusCode: {}. ErrorCode.code should be between 100 <= code <= 999",
                    e
            );
        }
    }

    public ResponseEntity<ErrorResponse> makeResponseEntity() {
        HttpStatusCode httpStatusCode = this.convertHttpStatusCode();
        return ResponseEntity.status(httpStatusCode).body(this);
    }
}
