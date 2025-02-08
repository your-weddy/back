package org.swyp.weddy.domain.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserResponse;
import org.swyp.weddy.domain.auth.service.dto.OAuthTokenResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2Service {
    @Value("${kakao.auth.tokenUrl}")
    private String tokenUrl;
    @Value("${kakao.auth.userInfoUrl}")
    private String userInfoUrl;
    @Value("${kakao.auth.client}")
    private String clientId;
    @Value("${kakao.auth.redirect}")
    private String redirectUri;
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<OAuthTokenResponse> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                kakaoTokenRequest,
                OAuthTokenResponse.class
        );

        return response.getBody().getAccessToken();
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.POST,
                kakaoUserInfoRequest,
                KakaoUserResponse.class
        );

        KakaoUserResponse userResponse = response.getBody();
        return KakaoUserInfo.builder()
                .oAuthId(userResponse.getOAuthId())
                .email(userResponse.getEmail())
                .nickname(userResponse.getNickname())
                .imgUrl(userResponse.getProfileImageUrl())
                .build();

    }
}

