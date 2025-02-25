package org.swyp.weddy.slow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.checklist.service.FilteringService;
import org.swyp.weddy.domain.checklist.service.dto.FilterByStatusDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

@Tag("slow")
@Transactional
@SpringBootTest
public class LargeCatSlowTest {
    @Autowired
    private FilteringService filteringService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DisplayName("진행 상황 기준으로 대분류 항목을 필터링할 수 있다")
    @Test
    void filter_large_cat_items_by_status() {
        jdbcTemplate.update("""
                update small_category_item
                set status_id = 1
                where title in ('스튜디오 / 스냅 예약' , '드레스투어 일정 예약' , '스튜디오 드레스 선택' , '본식 드레스 선택')
                and small_category_item.large_category_item_id = 3;
                """);

        jdbcTemplate.update("""
                update small_category_item
                set status_id = 1
                where title in ('신혼집 계약', '가전/가구 구매')
                and small_category_item.large_category_item_id = 8;
                        """);

        jdbcTemplate.update("""
                update small_category_item
                set status_id = 2
                where title in ('침구/생활용품 구매', '주방용품 구매', '신혼집 입주')
                and small_category_item.large_category_item_id = 8;
                """);

        List<LargeCatItemResponse> res = filteringService.filterByStatus(new FilterByStatusDto(1L, List.of("시작전")));
        List<LargeCatItemResponse> res2 = filteringService.filterByStatus(new FilterByStatusDto(1L, List.of("시작전", "진행중")));

        Assertions.assertThat(res).isNotNull();
        Assertions.assertThat(res2).isNotNull();
    }
}
