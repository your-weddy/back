package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilteringServiceTest {

    @DisplayName("진행상황 기준으로 필터링할 수 있다")
    @Nested
    class FilterByStatusTest {
        @DisplayName("진행상황 하나를 필터링 조건으로 입력받을 수 있다")
        @Test
        public void receive_one_status_as_filtering_condition() {
            FilteringService filteringService = new FilteringServiceImpl();
            filteringService.filterByStatus(1L, "시작전");
        }

        @DisplayName("진행상황 하나를 기준으로 필터링한 결과를 가져올 수 있다")
        @Test
        public void return_filtering_result_with_one_status() {
            FilteringService filteringService = new FilteringServiceImpl();
            List<LargeCatItemResponse> result = filteringService.filterByStatus(1L, "시작전");
            assertThat(result).isNotNull();
        }
    }
}
