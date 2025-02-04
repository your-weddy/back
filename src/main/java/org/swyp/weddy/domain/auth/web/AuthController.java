package org.swyp.weddy.domain.auth.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swyp.weddy.domain.auth.dao.UserMapper;
import org.swyp.weddy.domain.auth.service.AuthService;
import org.swyp.weddy.domain.auth.service.OAuth2TokenService;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login/kakao")
    public ResponseEntity<String> kakaoCallback(@RequestParam("code") String code) {
        log.debug("카카오 로그인 시작");

        authService.processKakaoLogin(code); // Service 계층 호출

        return ResponseEntity.ok("Login Success!");
    }
}