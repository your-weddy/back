package org.swyp.weddy.domain.checklist.service.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilteringDtoTest {

    @DisplayName("쉼표로 구분된 진행상황 문자열을 리스트로 만들 수 있다")
    @Test
    public void convert_string_separated_by_comma_into_list() {
        String statuses = "진행중,완료";
        List<String> statusList = FilteringDto.convertToList(statuses);
        assertThat(statusList).contains("진행중", "완료");
    }

    @DisplayName("진행상황 문자열에 포함된 공백 문자를 지우고 리스트로 만들 수 있다")
    @Test
    public void convert_string_with_space_separated_by_comma_into_list() {
        String statuses = "진행중 , 완료 , 시작전";
        List<String> statusList = FilteringDto.convertToList(statuses);
        assertThat(statusList).contains("진행중", "완료", "시작전");
    }

    @DisplayName("진행상황과 담당자 핉터링 조건을 선택적으로 가질 수 있다")
    @Test
    public void construct_with_one_filtering_condition() {
        FilteringDto filterByStatus = FilteringDto.of(1L, "진행중", "");
        assertThat(filterByStatus.getItemStatusList()).isNotNull();
        assertThat(filterByStatus.getItemAssigneeList().size()).isEqualTo(0);

        FilteringDto filterByAssignee = FilteringDto.of(1L, "", "신랑");
        assertThat(filterByAssignee.getItemStatusList().size()).isEqualTo(0);
        assertThat(filterByAssignee.getItemAssigneeList()).isNotNull();
    }

    @DisplayName("진행상황과 담당자 필터링 조건을 모두 가질 수 있다")
    @Test
    public void construct_with_two_filtering_conditions() {
        FilteringDto filterByStatus = FilteringDto.of(1L, "진행중", "신랑");
        assertThat(filterByStatus.getItemStatusList().size()).isGreaterThan(0);
        assertThat(filterByStatus.getItemAssigneeList().size()).isGreaterThan(0);
    }
}
