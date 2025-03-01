package org.swyp.weddy.domain.member.service.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberEditDtoTest {

    @DisplayName("변경요청 데이터를 받아, EditDto를 반환할 수 있다.")
    @Test
    void can_return_edit_dto() {
        //given
        Long memberId = 1L;
        String profileImageUrl = "test.img";

        // when
        MemberEditDto result = MemberEditDto.of(memberId, profileImageUrl);

        //then
        assertThat(result).isNotNull();

    }
}