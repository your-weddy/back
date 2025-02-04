package org.swyp.weddy.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.swyp.weddy.domain.auth.dao.UserMapper;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OAuth2TokenService oAuth2TokenService;
    private final UserMapper userMapper;

    public void processKakaoLogin(String code) {
        // 1. 액세스 토큰 받기
        String accessToken = oAuth2TokenService.getAccessToken(code);

        // 2. 사용자 정보 받기
        KakaoUserInfo userInfo = oAuth2TokenService.getUserInfo(accessToken);

        // 3. DB 처리
        KakaoUserInfo existingUser = userMapper.findByKakaoEmail(userInfo.getEmail());
        if (existingUser == null) {
            userMapper.saveUser(userInfo);
        } else {
            userMapper.updateUser(userInfo);
        }

        // 4. JWT 토큰 생성 (향후 구현 가능)
    }
}