package org.swyp.weddy.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;
import org.swyp.weddy.domain.member.service.dto.MemberEditDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;
    private String oAuthId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    public Member(Long id, String email, String name, String profileImageUrl, String oAuthId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.oAuthId = oAuthId;
    }
    public Member(String email, String name, String profileImageUrl, String oAuthId) {
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.oAuthId = oAuthId;
    }

    public Member(Long id, String email, String name, String profileImageUrl, String oAuthId, boolean isDeleted) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.oAuthId = oAuthId;
        this.isDeleted = isDeleted;
    }

    public static Member from(KakaoUserInfo kakaoUserInfo) {
        return new Member(
                kakaoUserInfo.getEmail(),
                kakaoUserInfo.getNickname(),
                kakaoUserInfo.getImgUrl(),
                String.valueOf(kakaoUserInfo.getOAuthId()));
    }

    public static Member ofEdit(Member member, MemberEditDto dto) {
        return new Member(
                member.getId(),
                dto.getEmail() == null ? member.getEmail() : dto.getEmail(),
                dto.getName() == null ? member.getName() : dto.getName(),
                dto.getProfileImageUrl() == null ? member.getProfileImageUrl() : dto.getProfileImageUrl(),
                dto.getOAuthId() == null ? member.getOAuthId() : dto.getOAuthId()
        );
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