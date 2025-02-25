package org.swyp.weddy.slow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.service.FilteringService;
import org.swyp.weddy.domain.checklist.service.dto.FilterByStatusDto;

import java.util.List;

@Tag("slow")
@Transactional
@SpringBootTest
public class FilteringSlowTest {
    @Autowired
    private FilteringService filteringService;
    @Autowired
    private LargeCatMapper largeCatMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DisplayName("largeCatMapper.selectAllItems() 검색 결과가 없을 때 반환값은 null이 아니다")
    @Test
    void returns_empty_list_when_no_result_in_select_query() {
        jdbcTemplate.update("""
                update large_category_item
                set is_deleted = true
                """);

        List<LargeCatItem> noResult = largeCatMapper.selectAllItems(1L);
        Assertions.assertThat(noResult).isEmpty();
    }

    @DisplayName("filteringService.filterByStatus() 검색 결과가 없을 때 예외를 반환한다")
    @Test
    void throws_exception_when_no_result_in_filter_by_status() {
        jdbcTemplate.update("""
                update large_category_item
                set is_deleted = true
                """);

        org.junit.jupiter.api.Assertions.assertThrows(LargeCatItemNotExistsException.class, () -> {
            filteringService.filterByStatus(new FilterByStatusDto(1L, List.of("시작전")));
        });
    }
}
