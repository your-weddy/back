package org.swyp.weddy.domain.auth.entity;

import lombok.*;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;
    private String oAuthId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted = false; // 기본값을 false로 설정

    public static Member from(KakaoUserInfo kakaoUserInfo) {
        return Member.builder()
                .email(kakaoUserInfo.getEmail())
                .name(kakaoUserInfo.getNickname())
                .profileImageUrl(kakaoUserInfo.getImgUrl())
                .oAuthId(String.valueOf(kakaoUserInfo.getOAuthId()))
                .build();
    }
}