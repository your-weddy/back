package org.swyp.weddy.domain.checklist.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.LargeCatService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemDeleteRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemEditRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LargeCatControllerTest {

    @DisplayName("getItem()")
    @Nested
    class GetItemTest {
        @DisplayName("대분류 항목 하나를 가져올 수 있다")
        @Test
        public void get_large_item() {
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
            ResponseEntity<LargeCatItemResponse> response = controller.getItem("1", "1");
            assertThat(response).isNotNull();
        }
    }

    @DisplayName("postItem()")
    @Nested
    class PostItemTest {
        @DisplayName("대분류 항목 추가 요청을 받을 수 있다")
        @Test
        public void post_large_item() {
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
            String memberId = "1L";
            String title = "test";
            LargeCatItemPostRequest request = new LargeCatItemPostRequest(memberId, title);

            controller.postItem(request);
        }

        @DisplayName("대분류 항목 추가 결과를 반환할 수 있다")
        @Test
        public void returns_post_large_item() {
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
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
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
            String memberId = "1";
            String itemId = "1";
            String editedTitle = "test_revised";

            LargeCatItemEditRequest request = new LargeCatItemEditRequest(memberId, itemId, editedTitle);

            controller.patchItem(request);
        }

        @DisplayName("대분류 항목 수정 결과를 반환할 수 있다")
        @Test
        public void returns_patch_large_item() {
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
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
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
            String memberId = "1";
            String itemId = "1";

            LargeCatItemDeleteRequest request = new LargeCatItemDeleteRequest(memberId, itemId);

            controller.deleteItem(request);
        }

        @DisplayName("대분류 항목 삭제 결과를 반환할 수 있다")
        @Test
        public void returns_delete_large_item() {
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
            String memberId = "1";
            String itemId = "1";

            LargeCatItemDeleteRequest request = new LargeCatItemDeleteRequest(memberId, itemId);

            assertThat(controller.deleteItem(request)).isEqualTo(ResponseEntity.ok().build());
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
        public List<LargeCatItemResponse> findAllItems(Long checklistId) {
            return null;
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
}
