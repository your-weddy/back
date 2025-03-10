package org.swyp.weddy.domain.auth.service;

import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;

public interface OAuth2Service {
    String getAccessToken(String code);

    KakaoUserInfo getUserInfo(String accessToken);
}
