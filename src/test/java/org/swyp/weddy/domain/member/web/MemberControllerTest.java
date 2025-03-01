package org.swyp.weddy.domain.member.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.member.service.MemberService;

import static org.mockito.Mockito.mock;

class MemberControllerTest {

    MemberService memberService;
    MemberController memberController;

    @BeforeEach
    void set_up(){
        memberService = mock(MemberService.class);
        memberController = new MemberController(memberService);
    }

    @Nested
    class UpdateProfileImageUrl{

        @DisplayName("사용자 프로필 이미지 업로드 요청을 받을 수 있다.")
        @Test
        void get_user_profile_image_request(){
            //given
            Long memberId = 1L;
            String profileImgUrl = "test.img";

            //when, then
            memberController.updateProfileImageUrl(memberId, profileImgUrl);
        }
    }

}