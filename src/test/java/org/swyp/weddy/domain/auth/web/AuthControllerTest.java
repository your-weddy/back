package org.swyp.weddy.domain.auth.web;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.auth.service.AuthService;
import org.swyp.weddy.domain.auth.service.CookieService;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    AuthService authService;
    CookieService cookieService;
    AuthController authController;

    @BeforeEach
    void set_up() {
        authService = mock(AuthService.class);
        cookieService = mock(CookieService.class);
        authController = new AuthController(authService, cookieService);
    }

    @Nested
    class KakaoCallbackTest {
        @DisplayName("사용자의 카카오 로그인 후, 카카오 서버는 인가 코드와 함께 redirect_uri로 리다이렉트한다")
        @Test
        void kakao_server_redirects_with_auth_code_after_user_login() throws IOException {
            HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

            when(authService.processKakaoLogin("x")).thenReturn(new TokenInfo("x", "y", "z"));

            // 사용자가 카카오 로그인에 성공한다

            // 카카오 서버는 인가 코드를 redirect_uri로 리다이렉트한다
            authController.kakaoCallback("x", httpServletResponse);
        }
    }

    @DisplayName("Invalid Member일 시 예외처리한다.")
    @Test
    void invalid_user_test() {
        //given
        when(authService.isValidMember()).thenReturn(false);

        //when,then
        assertThatThrownBy(() -> {
            authController.getAuthInfo();
        }).isInstanceOf(MemberNotFoundException.class);
    }

    @Nested
    class logoutTest {

        @DisplayName("로그아웃을 할 수 있다.")
        @Test
        void logout_return_success() {
            //given
            when(authService.isValidMember()).thenReturn(true);

            //when
            var result = authController.logout(mock(HttpServletResponse.class));

            //then
            assertEquals(200, result.getStatusCodeValue());
            verify(cookieService, times(1)).deleteCookie(any(HttpServletResponse.class));
        }

        @DisplayName("인증되지 않은 사용자 시 예외처리.")
        @Test
        void throws_exception_if_not_authorized() {
            //when, then
            assertThatThrownBy(() -> {
                 authController.logout(mock(HttpServletResponse.class));
            }).isInstanceOf(MemberNotFoundException.class);
        }
    }
}