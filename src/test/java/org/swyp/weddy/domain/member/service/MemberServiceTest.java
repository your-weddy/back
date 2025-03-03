package org.swyp.weddy.domain.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.member.dao.MemberMapper;
import org.swyp.weddy.domain.member.entity.Member;
import org.swyp.weddy.domain.member.exception.MemberUpdateException;
import org.swyp.weddy.domain.member.service.dto.MemberEditDto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    MemberEditDto memberEditDto;
    MemberMapper memberMapper;
    MemberService memberService;

    @BeforeEach
    void set_up(){
        memberEditDto = new MemberEditDto(1L, "test.Url");
        memberMapper = mock(MemberMapper.class);
        memberService = new MemberServiceImpl(memberMapper);
    }

    @Nested
    class updateTest {

        @DisplayName("프로필 이미지 변경을 성공할 수 있다")
        @Test
        void success_update_profile_image() {
            //given
            Member member = new Member(1L, "test@email.com", "testName", "test.img", "testOauthId");
            when(memberMapper.selectByMemberId(memberEditDto.getId())).thenReturn(member);
            when(memberMapper.updateMember(any(Member.class))).thenReturn(1);

            //when
            memberService.updateProfileImage(memberEditDto);

            //then
            verify(memberMapper, times(1)).selectByMemberId(memberEditDto.getId());
            verify(memberMapper, times(1)).updateMember(any(Member.class));

        }

        @DisplayName("변경 해야할 member가 db에 없을 시 예외처리")
        @Test
        void exception_when_member_not_exists() {
            //given
            when(memberMapper.selectByMemberId(memberEditDto.getId())).thenReturn(null);

            //when, then
            assertThatThrownBy(() -> {
                memberService.updateProfileImage(memberEditDto);
            }).isInstanceOf(MemberNotFoundException.class);

        }

        @DisplayName("변경이 되지 않았을 시 예외처리")
        @Test
        void exception_when_update_failed() {
            //given
            Member member = new Member(1L, "test@email.com", "testName", "test.img", "testOauthId");
            when(memberMapper.selectByMemberId(memberEditDto.getId())).thenReturn(member);
            when(memberMapper.updateMember(any(Member.class))).thenReturn(0);

            //when, then
            assertThatThrownBy(() -> {
                memberService.updateProfileImage(memberEditDto);
            }).isInstanceOf(MemberUpdateException.class);

        }
    }
}