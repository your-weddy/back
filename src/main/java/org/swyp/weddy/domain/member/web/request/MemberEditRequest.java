package org.swyp.weddy.domain.member.web.request;

public class MemberEditRequest {
    private Long id;
    private String email;
    private String name;
    private String profileImageUrl;

    public MemberEditRequest() {
    }

    public MemberEditRequest(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
