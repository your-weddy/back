package org.swyp.weddy.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {
    @DisplayName("ControllerAdvice 바깥에서 ErrorResponse 객체를 생성할 수 있다")
    @Test
    public void make_error_response_object() {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
        assertThat(errorResponse).isNotNull();
    }

    @DisplayName("ErrorResponse 내 정보를 가지고 ResponseEntity를 만들 수 있다")
    @Nested
    class convert2ResponseEntityTest {
        @DisplayName("ErrorResponse의 code를 ResponseEntity용 코드값으로 변환할 수 있다")
        @Test
        public void convert_code_to_http_status() {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
            HttpStatusCode httpStatusCode  = errorResponse.convertHttpStatusCode();
            assertThat(httpStatusCode.is4xxClientError()).isTrue();
        }

        @DisplayName("body를 ErrorResponse로 하는 ResponseEntity를 만들 수 있다")
        @Test
        public void make_response_entity_with_error_response_as_its_body() {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
            ResponseEntity<ErrorResponse> responseEntity = errorResponse.makeResponseEntity();
            assertThat(responseEntity.getStatusCode().is4xxClientError()).isTrue();
            assertThat(responseEntity.getBody()).isNotNull();
        }

        @DisplayName("body에 HttpStatusCode를 포함하지 않는다")
        @Test
        public void remove_http_status_code_from_error_response_body() throws JsonProcessingException {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST);
            ResponseEntity<ErrorResponse> responseEntity = errorResponse.makeResponseEntity();

            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(responseEntity.getBody());
            assertThat(s).isEqualTo("{" +
                    "\"code\":\"400\"," +
                    "\"reason\":\"잘못된 요청\"" +
                    "}");
            assertThat(s.contains("httpStatusCode")).isFalse();
        }
    }

    @DisplayName("code 값이 HttpStatusCode 범위 밖일 때 예외 처리할 수 있다")
    @Test
    public void handle_code_not_in_range() {
        //given
        ErrorCode mockErrorCode = Mockito.mock(ErrorCode.class);
        Mockito.when(mockErrorCode.getCode()).thenReturn("1000");
        ErrorResponse invalidErrorResponse = new ErrorResponse(mockErrorCode);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            invalidErrorResponse.convertHttpStatusCode();
        });
    }
}
