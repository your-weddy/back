package org.swyp.weddy.domain.checklist.web.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChecklistResponseTest {

    @DisplayName("결혼까지 남은 일자를 계산할 수 있다")
    @Nested
    class DaysBeforeWeddingComputeTest {
        @DisplayName("결혼 당일")
        @Test
        public void when_wedding_day() {
            assertThat(
                    ChecklistResponse.daysBeforeWedding(
                            LocalDate.of(2025, 3, 1),
                            LocalDate.of(2025, 3, 1)
                    )
            ).isEqualTo(0);
        }

        @DisplayName("결혼 전날")
        @Test
        public void one_day_before_wedding() {
            assertThat(
                    ChecklistResponse.daysBeforeWedding(
                            LocalDate.of(2025, 3, 2),
                            LocalDate.of(2025, 3, 1)
                    )
            ).isEqualTo(1);
        }

        @DisplayName("결혼 1개월 전")
        @Test
        public void one_month_before_wedding() {
            List<List<Integer>> integerLists = new ArrayList<List<Integer>>();
            integerLists.add(List.of(2,3,28));
            integerLists.add(List.of(3,4,31));
            integerLists.add(List.of(4,5,30));

            for (List<Integer> integerList : integerLists) {
                Integer todayMonth = integerList.get(0);
                Integer weddingMonth = integerList.get(1);
                Long expected = Long.valueOf(integerList.get(2));
                assertThat(
                        ChecklistResponse.daysBeforeWedding(
                                LocalDate.of(2025, weddingMonth, 1),
                                LocalDate.of(2025, todayMonth, 1)
                        )
                ).isEqualTo(expected);
            }
        }

        @DisplayName("결혼 1년 전")
        @Test
        public void one_year_before_wedding() {
            assertThat(
                    ChecklistResponse.daysBeforeWedding(
                            LocalDate.of(2026, 3, 1),
                            LocalDate.of(2025, 3, 1)
                    )
            ).isEqualTo(365);
        }
    }
}
