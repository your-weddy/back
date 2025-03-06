package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.exception.ChecklistAlreadyAssignedException;
import org.swyp.weddy.domain.checklist.exception.ChecklistNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDdayAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.request.ChecklistDdayAssignRequest;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChecklistServiceTest {

    @DisplayName("사용자에게 체크리스트를 할당할 수 있다")
    @Test
    public void add_checklist_to_member() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());
        assertThat(service.assignChecklist(dto)).isEqualTo(null);
    }

    @DisplayName("사용자에 할당된 체크리스트가 있는지 확인할 수 있다")
    @Test
    public void check_if_member_has_checklist() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());

        service.assignChecklist(dto);

        assertThat(service.hasChecklist(dto)).isEqualTo(true);
    }

    @DisplayName("사용자에게 할당된 체크리스트가 있다면, 추가로 할당하지 않는다")
    @Test
    public void prevent_adding_checklist_if_member_already_has_one() {
        String memberId = "1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());

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
        ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());
        service.assignChecklist(dto);

        ChecklistResponse checklistResponse = service.findChecklist(dto);
        Assertions.assertNotNull(checklistResponse);
    }

    @DisplayName("회원에게 할당된 체크리스트가 없을 경우, 예외를 던진다")
    @Test
    public void fail_to_find_checklist_by_id() {
        String memberId = "-1";
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());

        assertThrows(
                ChecklistNotExistsException.class,
                () -> service.findChecklist(dto)
        );
    }

    @DisplayName("editDday()")
    @Nested
    class EditDdayTest {
        @DisplayName("결혼 예정일 등록 요청을 받을 수 있다")
        @Test
        public void receive_assign_wedding_date_message() {
            ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());
            service.editDday(new ChecklistDdayAssignDto("2", LocalDate.of(2025,12,1)));
        }

        @DisplayName("컨트롤러로부터 결혼 예정일 등록 요청을 받을 수 있다")
        @Test
        public void receive_assign_wedding_date_message_from_controller() {
            ChecklistDdayAssignDto dto = ChecklistDdayAssignDto.from(
                    new ChecklistDdayAssignRequest("2", LocalDate.of(2025, 12, 1))
            );
            assertThat(dto).isNotNull();
        }

        @DisplayName("결혼 예정일을 등록할 수 있다")
        @Test
        public void assign_wedding_date() {
            ChecklistService service = new ChecklistServiceImpl(new FakeChecklistMapper());

            Long l = service.editDday(
                    new ChecklistDdayAssignDto(
                            "2",
                            LocalDate.of(2025, 12, 1)
                    )
            );
            assertThat(l).isEqualTo(2L);
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
            if (memberId == 2L) {
                return TestChecklist.from();
            }

            if (!hasChecklist) {
                return null;
            }

            ChecklistDto dto = ChecklistDto.from(memberId.toString());
            return Checklist.from(dto);
        }

        @Override
        public int updateChecklist(Checklist checklist) {
            return 0;
        }

        @Override
        public void insertTemplateData(Checklist checklist) {
        }
    }

    private static class TestChecklist extends Checklist {
        static Checklist from() {
            return new Checklist(
                    2L,
                    1L,
                    LocalDateTime.now(),
                    new Timestamp(System.currentTimeMillis()),
                    null,
                    Boolean.FALSE
            );
        }
    }
}
