package org.swyp.weddy.domain.checklist.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.FilteringService;
import org.swyp.weddy.domain.checklist.service.LargeCatService;
import org.swyp.weddy.domain.checklist.service.dto.*;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemDeleteRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemEditRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemMoveRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LargeCatControllerTest {
    private LargeCatController controller;

    @BeforeEach
    public void setUp() {
        controller = new LargeCatController(
                new FakeLargeCatService(),
                new FakeChecklistService(),
                new FakeFilteringService()
        );
    }

    @DisplayName("getItem()")
    @Nested
    class GetItemTest {
        @DisplayName("대분류 항목 하나를 가져올 수 있다")
        @Test
        public void get_large_item() {
            ResponseEntity<LargeCatItemResponse> response = controller.getItem("1", "1");
            assertThat(response).isNotNull();
        }
    }

    @DisplayName("getAllItems()")
    @Nested
    class GetAllItemsTest {
        @DisplayName("모든 대분류 항목 가져오기 요청을 받을 수 있다")
        @Test
        public void receive_get_all_large_items_message() {
            controller.getAllItems("1", "");
        }

        @DisplayName("모든 대분류 항목 가져오기 결과를 반환할 수 있다")
        @Test
        public void returns_all_large_items() {
            ResponseEntity<List<LargeCatItemResponse>> response = controller.getAllItems("1", "");

            assertThat(response).isNotNull();
        }

        @DisplayName("진행 상황 필터링 기준을 요청에 포함할 수 있다")
        @Test
        public void message_can_contain_status_filtering_condition() {
            controller.getAllItems("1", "시작전");
        }

        @DisplayName("주어진 진행 상황을 포함하는 모든 대분류 항목 가져오기 결과를 반환할 수 있다")
        @Test
        public void returns_all_large_items_filtered_by_status() {
            ResponseEntity<List<LargeCatItemResponse>> response = controller.getAllItems("1", "시작전");

            assertThat(response).isNotNull();
        }
    }

    @DisplayName("postItem()")
    @Nested
    class PostItemTest {
        @DisplayName("대분류 항목 추가 요청을 받을 수 있다")
        @Test
        public void post_large_item() {
            String memberId = "1L";
            String title = "test";
            LargeCatItemPostRequest request = new LargeCatItemPostRequest(memberId, title);

            controller.postItem(request);
        }

        @DisplayName("대분류 항목 추가 결과를 반환할 수 있다")
        @Test
        public void returns_post_large_item() {
            LargeCatItemPostRequest request = new LargeCatItemPostRequest("1L", "test");

            assertThat(controller.postItem(request)).isEqualTo(ResponseEntity.ok().build());
        }
    }

    @DisplayName("patchItem()")
    @Nested
    class PatchItemTest {
        @DisplayName("대분류 항목 수정 요청을 받을 수 있다")
        @Test
        public void patch_large_item() {
            String memberId = "1";
            String itemId = "1";
            String editedTitle = "test_revised";

            LargeCatItemEditRequest request = new LargeCatItemEditRequest(memberId, itemId, editedTitle);

            controller.patchItem(request);
        }

        @DisplayName("대분류 항목 수정 결과를 반환할 수 있다")
        @Test
        public void returns_patch_large_item() {
            String memberId = "1";
            String itemId = "1";
            String editedTitle = "test_revised";

            LargeCatItemEditRequest request = new LargeCatItemEditRequest(memberId, itemId, editedTitle);

            assertThat(controller.patchItem(request)).isEqualTo(ResponseEntity.ok().build());
        }
    }

    @DisplayName("deleteItem()")
    @Nested
    class DeleteItemTest {
        @DisplayName("대분류 항목 삭제 요청을 받을 수 있다")
        @Test
        public void receive_delete_large_item_message() {
            String memberId = "1";
            String itemId = "1";

            LargeCatItemDeleteRequest request = new LargeCatItemDeleteRequest(memberId, itemId);

            controller.deleteItem(request);
        }

        @DisplayName("대분류 항목 삭제 결과를 반환할 수 있다")
        @Test
        public void returns_delete_large_item() {
            String memberId = "1";
            String itemId = "1";

            LargeCatItemDeleteRequest request = new LargeCatItemDeleteRequest(memberId, itemId);

            assertThat(controller.deleteItem(request)).isEqualTo(ResponseEntity.ok().build());
        }
    }

    @DisplayName("moveItem()")
    @Nested
    class MoveItemTest {
        @DisplayName("대분류 항목 이동 요청을 받을 수 있다")
        @Test
        public void receive_move_large_item_message() {
            String memberId = "1";
            List<Long> idSequence = List.of();

            LargeCatItemMoveRequest request = new LargeCatItemMoveRequest(memberId, idSequence);

            controller.moveItem(request);
        }

        @DisplayName("대분류 항목 이동 결과를 반환할 수 있다")
        @Test
        public void returns_move_large_item() {
            String memberId = "1";
            List<Long> idSequence = List.of();

            LargeCatItemMoveRequest request = new LargeCatItemMoveRequest(memberId, idSequence);

            assertThat(controller.moveItem(request)).isEqualTo(ResponseEntity.ok().build());
        }
    }

    private static class FakeLargeCatService implements LargeCatService {
        @Override
        public LargeCatItemResponse findItem(Long checklistId, Long id) {
            if (checklistId == -1L) {
                return null;
            }
            return new LargeCatItemResponse(1L, 1L, "test");
        }

        @Override
        public LargeCatItemResponse findItemWithSmallItems(Long checklistId, Long id) {
            if (checklistId == -1L) {
                return null;
            }
            return new LargeCatItemResponse(1L, 1L, "test");
        }

        @Override
        public Long addItem(LargeCatItemAssignDto dto) {
            return 1L;
        }

        @Override
        public Long editItem(LargeCatItemEditDto dto) {
            return 1L;
        }

        @Override
        public Long deleteItem(LargeCatItemDeleteDto dto) {
            return 0L;
        }

        @Override
        public Long deleteItemWithSmallItems(LargeCatItemDeleteDto dto) {
            return 0L;
        }

        @Override
        public List<LargeCatItemResponse> findAllItems(Long checklistId) {
            return List.of(
                    new LargeCatItemResponse(1L, 1L, "test"),
                    new LargeCatItemResponse(1L, 1L, "test")
            );
        }

        @Override
        public void moveItem(LargeCatItemMoveDto dto) {
        }
    }

    private static class FakeChecklistService implements ChecklistService {

        @Override
        public Long assignChecklist(ChecklistDto dto) {
            return 0L;
        }

        @Override
        public boolean hasChecklist(ChecklistDto dto) {
            return false;
        }

        @Override
        public ChecklistResponse findChecklist(ChecklistDto dto) {
            if (dto.getMemberId().equals("-1")) {
                return new ChecklistResponse(-1L, "1L", 100);
            }
            return new ChecklistResponse(1L, "1L", 100);
        }
    }

    private static class FakeFilteringService implements FilteringService {

        @Override
        public List<LargeCatItemResponse> filterByStatus(FilterByStatusDto dto) {
            return List.of();
        }
    }
}
