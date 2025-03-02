package org.swyp.weddy.slow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.member.dao.MemberMapper;
import org.swyp.weddy.domain.member.entity.Member;
import org.swyp.weddy.domain.member.service.MemberService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("slow")
@Transactional
@SpringBootTest
public class MemberSlowTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper memberMapper;


    @DisplayName("1명의 멤버를 업데이트 한다.")
    @Test
    void success_update_1_row_of_member() {
        //given
        Member member = new Member(1L, "test@email.com", "testName", "test.img", "testOauthId");

        //when
        int result = memberMapper.updateMember(member);

        //then
        assertEquals(result, 1);

    }

    @DisplayName("1명의 멤버를 성공적으로 업데이트 한다.")
    @Test
    void member_update() {
        //given
        Member member = new Member(1L, "test@email.com", "testName", "test.img", "testOauthId");

        //when
        memberMapper.updateMember(member);

        //then
        Member result = memberMapper.selectByMemberId(1L);
        assertEquals(result.getEmail(), member.getEmail());
        assertEquals(result.getName(), member.getName());
    }

}
