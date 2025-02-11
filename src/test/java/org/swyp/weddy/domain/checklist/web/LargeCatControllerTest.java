package org.swyp.weddy.domain.checklist.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.LargeCatService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import static org.assertj.core.api.Assertions.assertThat;

class LargeCatControllerTest {

    @DisplayName("getItem()")
    @Nested
    class getItemTest {
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

        @DisplayName("대분류 항목이 없는 경우를 처리할 수 있다")
        @Test
        public void fail_to_get_large_item() {
            LargeCatController controller = new LargeCatController(
                    new FakeLargeCatService(),
                    new FakeChecklistService()
            );
            Assertions.assertThrows(LargeCatItemNotExistsException.class, () -> {
                controller.getItem("-1", "-1");
            });
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
