package org.swyp.weddy.common.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorCodeTest {

    @DisplayName("ErrorCode는 code 필드로 정수값을 받는다")
    @Test
    public void make_error_code() {
        assertThat(ErrorCode.NOT_EXISTS.getCode()).isInstanceOf(Integer.class);
    }
}
