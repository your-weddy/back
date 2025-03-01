package org.swyp.weddy.domain.member.web;

import org.junit.jupiter.api.*;
import org.swyp.weddy.domain.member.service.MemberService;

import static org.mockito.Mockito.mock;

class MemberControllerTest {

    Long memberId;
    String profileImgUrl;
    MemberService memberService;
    MemberController memberController;

    @BeforeEach
    void set_up(){
        Long memberId = 1L;
        String profileImgUrl = "test.img";
        memberService = mock(MemberService.class);
        memberController = new MemberController(memberService);
    }

    @Nested
    class UpdateProfileImageUrl{

        @DisplayName("사용자 프로필 이미지 변경 요청을 받을 수 있다.")
        @Test
        void get_user_profile_image_request(){
            //when, then
            memberController.updateProfileImageUrl(memberId, profileImgUrl);
        }

        @DisplayName("사용자 프로필 이미지 변경 응답에 성공 할 수 있다.")
        @Test
        void success_user_profile_image_request(){
            //when
            var result = memberController.updateProfileImageUrl(memberId, profileImgUrl);

            //then
            Assertions.assertEquals(200, result.getStatusCodeValue());
        }
    }

}