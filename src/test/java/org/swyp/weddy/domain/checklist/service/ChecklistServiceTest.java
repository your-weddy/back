package org.swyp.weddy.domain.checklist.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;

class ChecklistServiceTest {
    private ChecklistService service;

    @DisplayName("사용자에게 체크리스트를 할당할 수 있다")
    @Test
    public void add_checklist_to_member() {
        String memberId = "1";
        service = new FakeChecklistService(new FakeChecklistMapper());
        Assertions.assertThat(service.assignChecklist(memberId)).isEqualTo("checklist_id");
    }

    private static class FakeChecklistService implements ChecklistService {
        private ChecklistMapper mapper;

        public FakeChecklistService(ChecklistMapper mapper) {
            this.mapper = mapper;
        }

        public String assignChecklist(String memberId) {
            return mapper.insertChecklist(memberId);
        }
    }

    private static class FakeChecklistMapper implements ChecklistMapper {
        @Override
        public String insertChecklist(String memberId) {
            return "checklist_id";
        }
    }
}
