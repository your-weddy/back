package org.swyp.weddy.domain.auth.web;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.auth.exception.UserNotFoundException;
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
    void setUp() {
        authService = mock(AuthService.class);
        cookieService = mock(CookieService.class);
        authController = new AuthController(authService, cookieService);
    }

    @DisplayName("Invalid User일 시 예외처리한다.")
    @Test
    void InvalidUserTest() {
        //given
        when(authService.isValidUser()).thenReturn(false);

        //when,then
        assertThatThrownBy(() -> {
            authController.getUserInfo();
        }).isInstanceOf(UserNotFoundException.class);
    }

    @Nested
    class logoutTest {

        @DisplayName("로그아웃을 할 수 있다.")
        @Test
        void testLogout_success() {
            //given
            when(authService.isValidUser()).thenReturn(true);

            //when
            var result = authController.logout(mock(HttpServletResponse.class));

            //then
            assertEquals(200, result.getStatusCodeValue());
            verify(cookieService, times(1)).deleteCookies(any(HttpServletResponse.class));
        }

        @DisplayName("인증되지 않은 사용자 시 예외처리.")
        @Test
        void throws_Exception_If_Not_Authorized() {
            //when, then
            assertThatThrownBy(() -> {
                var result = authController.logout(mock(HttpServletResponse.class));
            }).isInstanceOf(UserNotFoundException.class);
        }
    }
}