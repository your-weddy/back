package org.swyp.weddy.domain.auth.service.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfo {
//    private String id;
    private String email;
    private String nickname;
}
