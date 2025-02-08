package org.swyp.weddy.domain.auth.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserResponse {

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonProperty("id")
    private Long oAuthId;

    @Getter
    @NoArgsConstructor
    private static class KakaoAccount {
        @JsonProperty("email")
        private String email;

        @JsonProperty("profile")
        private Profile profile;

    }
    @Getter
    @NoArgsConstructor
    private static class Profile {
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("thumbnail_image_url")
        private String thumbnailImageUrl;

        @JsonProperty("profile_image_url")
        private String profileImageUrl;
    }
    public String getEmail() {
        return this.getKakaoAccount().getEmail();
    }
    public String getNickname() {
        return this.getKakaoAccount().getProfile().getNickname();
    }

    public String getProfileImageUrl() {
        return this.getKakaoAccount().getProfile().getProfileImageUrl();
    }


}
