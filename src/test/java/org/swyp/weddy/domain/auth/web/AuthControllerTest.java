package org.swyp.weddy.domain.auth.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.auth.exception.UserNotFoundException;
import org.swyp.weddy.domain.auth.service.AuthService;
import org.swyp.weddy.domain.auth.service.CookieService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}