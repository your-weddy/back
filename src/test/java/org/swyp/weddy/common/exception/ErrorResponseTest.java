package org.swyp.weddy.common.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

class ErrorResponseTest {
    @DisplayName("ControllerAdvice 바깥에서 ErrorResponse 객체를 생성할 수 있다")
    @Test
    public void make_error_response_object() {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
        Assertions.assertThat(errorResponse).isNotNull();
    }

    @DisplayName("ErrorResponse 내 정보를 가지고 ResponseEntity를 만들 수 있다")
    @Nested
    class convert2ResponseEntityTest {
        @DisplayName("ErrorResponse의 code를 ResponseEntity용 코드값으로 변환할 수 있다")
        @Test
        public void convert_code_to_http_status() {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
            String code = errorResponse.getCode();
            Integer iCode = Integer.valueOf(code);
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(iCode);
            Assertions.assertThat(httpStatusCode.is4xxClientError()).isTrue();
        }

        @DisplayName("body를 ErrorResponse로 하는 ResponseEntity를 만들 수 있다")
        @Test
        public void make_response_entity_with_error_response_as_its_body() {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
            String code = errorResponse.getCode();
            Integer iCode = Integer.valueOf(code);
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(iCode);
            ResponseEntity<ErrorResponse> responseEntity = ResponseEntity.status(httpStatusCode).body(errorResponse);
            Assertions.assertThat(responseEntity.getStatusCode().is4xxClientError()).isTrue();
            Assertions.assertThat(responseEntity.getBody()).isNotNull();
        }
    }
}
