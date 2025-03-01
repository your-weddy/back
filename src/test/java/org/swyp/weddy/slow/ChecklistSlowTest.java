package org.swyp.weddy.slow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDdayAssignDto;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("slow")
@Transactional
@SpringBootTest
public class ChecklistSlowTest {
    @Autowired
    private ChecklistService checklistService;
    @Autowired
    private ChecklistMapper checklistMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DisplayName("checklist의 d_day 컬럼 값을 바꿀 수 있다")
    @Test
    void returns_empty_list_when_no_result_in_select_query() {
        jdbcTemplate.update("""
                update checklist
                set d_day = null
                where id = 1
                """);

        Checklist checklist = checklistMapper.selectChecklistByMemberId(1L);
        ChecklistDdayAssignDto dto = new ChecklistDdayAssignDto("1", LocalDate.now());
        Checklist editedChecklist = Checklist.withNewDday(checklist, dto);
        int editedId = checklistMapper.updateChecklist(editedChecklist);

        assertThat(editedId).isEqualTo(1);

        Checklist check = checklistMapper.selectChecklistByMemberId(Long.valueOf(editedId));
        assertThat(check.getdDay()).isNotNull();
    }
}
