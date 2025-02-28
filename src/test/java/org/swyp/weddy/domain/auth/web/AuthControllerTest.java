package org.swyp.weddy.domain.auth.web;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.auth.service.AuthService;
import org.swyp.weddy.domain.auth.service.CookieService;

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

    @DisplayName("Invalid User일 시 예외처리한다.")
    @Test
    void invalid_user_test() {
        //given
        when(authService.isValidMember()).thenReturn(false);

        //when,then
        assertThatThrownBy(() -> {
            authController.getMemberInfo();
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