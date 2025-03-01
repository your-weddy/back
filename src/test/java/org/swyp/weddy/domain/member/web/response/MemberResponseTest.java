package org.swyp.weddy.domain.member.web.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.member.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

class MemberResponseTest {

    @DisplayName("Member객체로부터 MemberResponse를 만들 수 있다.")
    @Test
    void can_create_response_from_member(){
        //given
        Member member = new Member("test@email.com", "test_name","test.img", "123");

        //when
        MemberResponse result = MemberResponse.from(member);

        //then
        assertThat(result).isNotNull();
    }

}