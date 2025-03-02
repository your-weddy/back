package org.swyp.weddy.domain.member.service.dto;

import lombok.Getter;

@Getter
public class MemberEditDto {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;
    private String oAuthId;

    public MemberEditDto(Long id, String profileImageUrl) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
    }

    public static MemberEditDto of(Long memberId, String profileImageUrl) {
        return new MemberEditDto(
                memberId,
                profileImageUrl
        );

    }
}
