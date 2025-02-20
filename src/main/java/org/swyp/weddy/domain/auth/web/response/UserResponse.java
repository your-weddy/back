package org.swyp.weddy.domain.auth.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.swyp.weddy.domain.auth.entity.Member;

@AllArgsConstructor
@Getter
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;

    public static UserResponse from(Member member) {
        return UserResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}


