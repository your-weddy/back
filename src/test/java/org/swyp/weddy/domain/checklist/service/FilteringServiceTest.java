package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.service.dto.FilterByStatusDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilteringServiceTest {

    @DisplayName("진행상황 기준으로 필터링할 수 있다")
    @Nested
    class FilterByStatusTest {
        @DisplayName("진행상황 하나를 필터링 조건으로 입력받을 수 있다")
        @Test
        public void receive_one_status_as_filtering_condition() {
            FilteringService filteringService = new FilteringServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            filteringService.filterByStatus(new FilterByStatusDto(1L, "시작전"));
        }

        @DisplayName("진행상황 하나를 기준으로 필터링한 결과를 가져올 수 있다")
        @Test
        public void return_filtering_result_with_one_status() {
            FilteringService filteringService = new FilteringServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            List<LargeCatItemResponse> result = filteringService.filterByStatus(new FilterByStatusDto(1L, "시작전"));
            assertThat(result).isNotNull();
        }
    }

    private static class FakeLargeCatMapper implements LargeCatMapper {
        @Override
        public LargeCatItem selectItem(Long checklistId, Long id) {
            return null;
        }

        @Override
        public List<LargeCatItem> selectAllItems(Long checklistId) {
            return List.of();
        }

        @Override
        public Long insertItem(LargeCatItem item) {
            return 0L;
        }

        @Override
        public Long updateItem(LargeCatItem item) {
            return 0L;
        }

        @Override
        public Long deleteItem(LargeCatItem item) {
            return 0L;
        }
    }

    private static class FakeSmallCatService implements SmallCatService {
        @Override
        public List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId) {
            return List.of();
        }

        @Override
        public SmallCatItemResponse findItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
            return null;
        }

        @Override
        public Long addItem(SmallCatItemDto dto) {
            return 0L;
        }

        @Override
        public boolean editItem(SmallCatItemDto dto) {
            return false;
        }

        @Override
        public boolean deleteItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
            return false;
        }

        @Override
        public boolean deleteAll(Long checklistId, Long largeCatItemId) {
            return false;
        }
    }
}
