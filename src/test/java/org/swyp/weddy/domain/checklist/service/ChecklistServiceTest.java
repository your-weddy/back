package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.exception.ChecklistAlreadyAssignedException;
import org.swyp.weddy.domain.checklist.exception.ChecklistNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChecklistServiceTest {

    @DisplayName("사용자에게 체크리스트를 할당할 수 있다")
    @Test
    public void add_checklist_to_member() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());
        assertThat(service.assignChecklist(dto)).isEqualTo(1);
    }

    @DisplayName("사용자에 할당된 체크리스트가 있는지 확인할 수 있다")
    @Test
    public void check_if_member_has_checklist() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());

        service.assignChecklist(dto);

        assertThat(service.hasChecklist(dto)).isEqualTo(true);
    }

    @DisplayName("사용자에게 할당된 체크리스트가 있다면, 추가로 할당하지 않는다")
    @Test
    public void prevent_adding_checklist_if_member_already_has_one() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());

        service.assignChecklist(dto);
        Assertions.assertThrows(ChecklistAlreadyAssignedException.class, () ->
                service.assignChecklist(dto));
    }

    @Test
    public void test_input_of_Long_valueOf() {
        String sLong = "1";
        assertThat(Long.valueOf(sLong)).isEqualTo(1L);

        String sLong2 = "1l";
        assertThrows(NumberFormatException.class, () -> Long.valueOf(sLong2));
    }

    @DisplayName("회원 아이디에 할당된 체크리스트를 가져올 수 있다")
    @Test
    public void find_checklist_by_id() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());
        service.assignChecklist(dto);

        ChecklistResponse checklistResponse = service.findChecklist(dto);
        Assertions.assertNotNull(checklistResponse);
    }

    @DisplayName("회원에게 할당된 체크리스트가 없을 경우, 예외를 던진다")
    @Test
    public void fail_to_find_checklist_by_id() {
        String memberId = "-1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new FakeChecklistService(new FakeChecklistMapper());

        assertThrows(
                ChecklistNotExistsException.class,
                () -> service.findChecklist(dto)
        );
    }

    private static class FakeChecklistService implements ChecklistService {
        private final ChecklistMapper mapper;

        public FakeChecklistService(ChecklistMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public Long assignChecklist(ChecklistDto dto) {
            if (hasChecklist(dto)) {
                throw new ChecklistAlreadyAssignedException(ErrorCode.BAD_REQUEST);
            }

            return Long.valueOf(mapper.insertChecklist(null));
        }

        @Override
        public boolean hasChecklist(ChecklistDto dto) {
            Long memberId = Long.valueOf(dto.getMemberId());
            Checklist checklist = mapper.selectChecklistByMemberId(memberId);
            return checklist != null;
        }

        @Override
        public ChecklistResponse findChecklist(ChecklistDto dto) {
            Long memberId = Long.valueOf(dto.getMemberId());
            Checklist checklist = mapper.selectChecklistByMemberId(memberId);

            if (checklist == null) {
                throw new ChecklistNotExistsException(ErrorCode.NOT_EXISTS);
            }

            return ChecklistResponse.from(Checklist.from(dto));
        }
    }

    private static class FakeChecklistMapper implements ChecklistMapper {
        private boolean hasChecklist = false;

        @Override
        public int insertChecklist(Checklist checklist) {
            hasChecklist = true;
            return 1;
        }

        @Override
        public Checklist selectChecklistByMemberId(Long memberId) {
            if (!hasChecklist) {
                return null;
            }

            ChecklistDto dto = ChecklistDto.from(memberId.toString());
            return Checklist.from(dto);
        }
    }
}
