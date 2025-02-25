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

import java.util.List;

@Tag("slow")
@Transactional
@SpringBootTest
public class FilteringSlowTest {
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
}
