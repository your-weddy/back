package org.swyp.weddy.domain.auth.web.response;

import org.swyp.weddy.domain.member.entity.Member;

public class AuthResponse {
    private Long id;

    public AuthResponse(Long id) {
        this.id = id;
    }

    public static AuthResponse from(Member member) {
        return new AuthResponse(member.getId());
    }
}


