package org.swyp.weddy.domain.checklist.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.request.ChecklistDdayAssignRequest;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

import java.time.LocalDate;

class ChecklistControllerTest {

    @DisplayName("assignWeddingDate()")
    @Nested
    class AssignWeddingDateTest {
        @DisplayName("결혼 예정일 등록 요청을 받을 수 있다")
        @Test
        public void receive_assign_wedding_date_message() {
            ChecklistController controller = new ChecklistController(new FakeChecklistService());
            controller.assignWeddingDate("1", new ChecklistDdayAssignRequest());
        }

        @DisplayName("요청은 날짜 정보를 포함한다")
        @Test
        public void date_in_message_follows_format() {
            ChecklistController controller = new ChecklistController(new FakeChecklistService());
            controller.assignWeddingDate("1", new ChecklistDdayAssignRequest(LocalDate.of(2025, 12, 1)));
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
            return null;
        }
    }
}
