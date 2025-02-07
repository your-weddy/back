package org.swyp.weddy.domain.checklist.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

class ChecklistServiceTest {

    @DisplayName("사용자에게 체크리스트를 할당할 수 있다")
    @Test
    public void add_checklist_to_member() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());
        Assertions.assertThat(service.assignChecklist(dto)).isEqualTo(1);
    }

    @DisplayName("사용자에 할당된 체크리스트가 있는지 확인할 수 있다")
    @Test
    public void check_if_member_has_checklist() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());
        Assertions.assertThat(service.hasChecklist(dto)).isEqualTo(true);
    }

    private static class FakeChecklistService implements ChecklistService {
        private final ChecklistMapper mapper;

        public FakeChecklistService(ChecklistMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public int assignChecklist(ChecklistDto dto) {
            return mapper.insertChecklist(null);
        }

        @Override
        public boolean hasChecklist(ChecklistDto dto) {
            return true;
        }
    }

    private static class FakeChecklistMapper implements ChecklistMapper {
        @Override
        public int insertChecklist(Checklist checklist) {
            return 1;
        }
    }
}
