package org.swyp.weddy.domain.checklist.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

class ChecklistTest {

    @DisplayName("d-day 필드는 결혼 예정일 날짜 정보를 갖는다")
    @Test
    public void d_day_field_have_date_info() {
        Checklist checklist = TestChecklist.from();
        Assertions.assertThat(checklist.getdDay()).isInstanceOf(LocalDateTime.class);
    }

    private static class TestChecklist extends Checklist {
        static Checklist from() {
            return new Checklist(
                    1L,
                    LocalDateTime.now(),
                    new Timestamp(System.currentTimeMillis()),
                    null,
                    Boolean.FALSE
            );
        }
    }
}
