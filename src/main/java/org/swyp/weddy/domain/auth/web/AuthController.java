package org.swyp.weddy.domain.auth.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.auth.exception.JwtRefreshTokenInvalidException;
import org.swyp.weddy.domain.auth.exception.UserNotFoundException;
import org.swyp.weddy.domain.auth.service.AuthService;
import org.swyp.weddy.domain.auth.service.CookieService;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;
import org.swyp.weddy.domain.auth.web.response.UserResponse;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${app.home-page-url}")
    private String HOME_PAGE_URL;
    private final AuthService authService;
    private final CookieService cookieService;

    @GetMapping("/kakao/callback")
    public void kakaoCallback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        TokenInfo tokenInfo = authService.processKakaoLogin(code);

        cookieService.setCookies(response, tokenInfo);

        response.sendRedirect(HOME_PAGE_URL);
    }

    //access토큰 만료 시 재발급
    @GetMapping("/regenerate-token")
    public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> token = authService.resolveToken(request);

            String refreshToken = token.get("refreshToken");
            TokenInfo newTokenInfo = authService.generateNewTokenIfRefreshTokenValid(refreshToken);

            cookieService.setCookies(response, newTokenInfo);

            return ResponseEntity.ok().build();
        } catch (JwtRefreshTokenInvalidException e) {
            return ResponseEntity.status(ErrorCode.TOKEN_INVALID.getCode()).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUserInfo() {

        if(!authService.isValidUser()){
            throw new UserNotFoundException(ErrorCode.UNAUTHORIZED);
        }

        UserResponse userResponse = authService.getUserInfo();
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {

        if(!authService.isValidUser()){
            throw new UserNotFoundException(ErrorCode.UNAUTHORIZED);
        }

        cookieService.deleteCookies(response);
        return ResponseEntity.ok().build();
    }

}