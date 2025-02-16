package org.swyp.weddy.common.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorResponseTest {
    @DisplayName("ControllerAdvice 바깥에서 ErrorResponse 객체를 생성할 수 있다")
    @Test
    public void make_error_response_object() {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
        Assertions.assertThat(errorResponse).isNotNull();
    }
}
