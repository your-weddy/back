package org.swyp.weddy.domain.member.entity;

import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;
    private String oAuthId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    public Member(String email, String name, String profileImageUrl, String oAuthId) {
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.oAuthId = oAuthId;
    }

    public static Member from(KakaoUserInfo kakaoUserInfo) {
        return new Member(
                kakaoUserInfo.getEmail(),
                kakaoUserInfo.getNickname(),
                kakaoUserInfo.getImgUrl(),
                String.valueOf(kakaoUserInfo.getOAuthId()));
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getOAuthId() {
        return oAuthId;
    }
}