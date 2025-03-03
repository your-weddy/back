package org.swyp.weddy.domain.member.web.response;

import org.swyp.weddy.domain.member.entity.Member;

public class MemberResponse {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;

    public MemberResponse(Long id, String email, String name, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getProfileImageUrl());

    }
}


