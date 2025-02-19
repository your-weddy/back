package org.swyp.weddy.domain.auth.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.auth.exception.JwtRefreshTokenInvalidException;
import org.swyp.weddy.domain.auth.service.AuthService;
import org.swyp.weddy.domain.auth.service.CookieService;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;

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

    @GetMapping("/login/kakao")
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
            return ResponseEntity.status(Integer.parseInt(ErrorCode.TOKEN_INVALID.getCode())).build();
        }
    }

    /*@GetMapping("/userinfo")
    public ResponseEntity<String> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자일 경우 에러 응답 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();

        return ResponseEntity.ok("ok");
    }*/

}