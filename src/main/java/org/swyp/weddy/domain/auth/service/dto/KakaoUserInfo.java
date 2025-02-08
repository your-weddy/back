package org.swyp.weddy.domain.auth.service.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfo {
    private Integer id;
    private Long oAuthId;
    private String email;
    private String nickname;
    private String imgUrl;
}
