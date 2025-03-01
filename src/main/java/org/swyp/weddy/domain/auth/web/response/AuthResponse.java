package org.swyp.weddy.domain.auth.web.response;

import lombok.Getter;
import org.swyp.weddy.domain.member.entity.Member;

@Getter
public class AuthResponse {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;

    public AuthResponse(Long id, String email, String name, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public static AuthResponse from(Member member) {
        return new AuthResponse(member.getId(), member.getEmail(), member.getName(), member.getProfileImageUrl());
    }
}


