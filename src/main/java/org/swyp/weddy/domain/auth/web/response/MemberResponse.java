package org.swyp.weddy.domain.auth.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.swyp.weddy.domain.auth.entity.Member;

@AllArgsConstructor
@Getter
@Builder
public class MemberResponse {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}


