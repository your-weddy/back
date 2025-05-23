package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.service.dto.FilteringDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemMoveDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemSelectDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilteringServiceTest {
    private FilteringService filteringService;

    @BeforeEach
    public void setUp() {
         filteringService = new FilteringServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
    }

    @DisplayName("필터링 조건 하나를 기준으로 필터링할 수 있다")
    @Nested
    class FilterByOneConditionTest {
        @DisplayName("필터링 기준 하나를 필터링 조건으로 입력받을 수 있다")
        @Test
        public void receive_one_status_as_filtering_condition() {
            filteringService.filterBy(new FilteringDto(1L, List.of("시작전"), Collections.emptyList()));
            filteringService.filterBy(new FilteringDto(1L, Collections.emptyList(), List.of("신랑")));
        }

        @DisplayName("진행상황 하나를 기준으로 필터링한 결과를 가져올 수 있다")
        @Test
        public void return_filtering_result_with_one_status() {
            List<LargeCatItemResponse> filtered = filteringService.filterBy(new FilteringDto(1L, List.of("시작전"), Collections.emptyList()));
            var filteredSmall = filtered.get(0).getSmallCatItems().stream().filter(x -> x.getStatusName().equals("시작전")).toList();
            assertThat(filteredSmall).isNotNull();
        }

        @DisplayName("진행상황 두개를 기준으로 필터링한 결과를 가져올 수 있다")
        @Test
        public void return_filtering_result_with_two_status() {
            List<LargeCatItemResponse> filtered = filteringService.filterBy(new FilteringDto(2L, List.of("시작전", "진행중"), Collections.emptyList()));
            var filteredSmall = filtered.get(0).getSmallCatItems().stream().filter(x -> {
                return x.getStatusName().equals("시작전") || x.getStatusName().equals("진행중");
            }).toList();
            assertThat(filteredSmall).isNotNull();
        }

        @DisplayName("담당자 하나를 기준으로 필터링한 결과를 가져올 수 있다")
        @Test
        public void return_filtering_result_with_one_assignee() {
            List<LargeCatItemResponse> filtered = filteringService.filterBy(new FilteringDto(3L, Collections.emptyList(), List.of("신랑")));
            var filteredSmall = filtered.get(0).getSmallCatItems().stream().filter(x -> x.getAssigneeName().equals("신랑")).toList();
            assertThat(filteredSmall).isNotNull();
        }

        @DisplayName("담당자 두개를 기준으로 필터링한 결과를 가져올 수 있다")
        @Test
        public void return_filtering_result_with_two_assignees() {
            List<LargeCatItemResponse> filtered = filteringService.filterBy(new FilteringDto(4L, Collections.emptyList(), List.of("신랑", "신부")));
            var filteredSmall = filtered.get(0).getSmallCatItems().stream().filter(x -> {
                return x.getAssigneeName().equals("신랑") || x.getAssigneeName().equals("신부");
            }).toList();
            assertThat(filteredSmall).isNotNull();
        }
    }

    private static class TestLargeCatItem extends LargeCatItem {
        static LargeCatItem of(Long id, Long checklistId, String title) {
            return new LargeCatItem(
                    id,
                    checklistId,
                    title,
                    new Timestamp(System.currentTimeMillis()),
                    null,
                    Boolean.FALSE
            );
        }
    }

    private static class TestSmallCatItemPreviewResponse extends SmallCatItemPreviewResponse {
        static SmallCatItemPreviewResponse of(Long id, Long largeCatItemId, String assigneeName, String statusName) {
            return new SmallCatItemPreviewResponse(
                    id,
                    largeCatItemId,
                    "",
                    null,
                    assigneeName,
                    statusName,
                    1L
            );
        }
    }

    private static class FakeLargeCatMapper implements LargeCatMapper {
        @Override
        public LargeCatItem selectItem(Long checklistId, Long id) {
            return null;
        }

        @Override
        public List<LargeCatItem> selectAllItems(Long checklistId) {
            return switch (checklistId.intValue()) {
                case 1 -> List.of(TestLargeCatItem.of(1L, 1L, "신혼집"));
                case 2 -> List.of(TestLargeCatItem.of(2L, 1L, "신혼집"));
                case 3 -> List.of(TestLargeCatItem.of(3L, 1L, "신혼집"));
                case 4 -> List.of(TestLargeCatItem.of(4L, 1L, "신혼집"));
                default -> throw new RuntimeException("Unexpected");
            };
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

        @Override
        public int updateItemSequence(LargeCatItem item) {
            return 0;
        }
    }

    private static class FakeSmallCatService implements SmallCatService {
        @Override
        public List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId) {
            return List.of();
        }

        @Override
        public List<SmallCatItemPreviewResponse> findItemPreviewsByChecklistId(Long checklistId) {
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

        @Override
        public boolean moveItem(SmallCatItemMoveDto dto) {
            return false;
        }

        @Override
        public List<SmallCatItemPreviewResponse> findItemPreviewsBy(SmallCatItemSelectDto dto) {
            return switch (dto.getChecklistId().intValue()) {
                case 1 -> List.of(
                        TestSmallCatItemPreviewResponse.of(1L, 1L, null, "시작전"),
                        TestSmallCatItemPreviewResponse.of(2L, 1L, null, "시작전")
                        );
                case 2 -> List.of(
                        TestSmallCatItemPreviewResponse.of(1L, 1L, null, "시작전"),
                        TestSmallCatItemPreviewResponse.of(3L, 1L, null, "진행중")
                );
                case 3 -> List.of(
                        TestSmallCatItemPreviewResponse.of(1L, 1L, "신랑", null),
                        TestSmallCatItemPreviewResponse.of(3L, 1L, "신랑", null)
                );
                case 4 -> List.of(
                        TestSmallCatItemPreviewResponse.of(1L, 1L, "신랑", null),
                        TestSmallCatItemPreviewResponse.of(3L, 1L, "신부", null)
                );
                default -> throw new RuntimeException("Unexpected");
            };
        }
    }
}
