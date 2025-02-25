package org.swyp.weddy.domain.checklist.service.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilterByStatusDtoTest {

    @DisplayName("쉼표로 구분된 진행상황 문자열을 리스트로 만들 수 있다")
    @Test
    public void convert_string_separated_by_comma_into_list() {
        String statuses = "진행중,완료";
        List<String> statusList = Arrays.stream(statuses.split(",")).toList();
        assertThat(statusList).contains("진행중", "완료");
    }

    @DisplayName("진행상황 문자열에 포함된 공백 문자를 지우고 리스트로 만들 수 있다")
    @Test
    public void convert_string_with_space_separated_by_comma_into_list() {
        String statuses = "진행중 , 완료 , 시작전";
        List<String> statusList = Arrays.stream(statuses.split(",")).map(String::strip).toList();
        assertThat(statusList).contains("진행중", "완료", "시작전");
    }
}
