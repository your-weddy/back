package org.swyp.weddy.domain.auth.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserResponse {

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonProperty("properties")
    private KakaoProperties properties;

    @Getter
    @NoArgsConstructor
    public static class KakaoAccount{
        String email;
    }
    @Getter
    @NoArgsConstructor
    public static class KakaoProperties{
        String nickname;
    }
}
