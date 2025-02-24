package org.swyp.weddy.domain.checklist.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.checklist.service.SmallCatService;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemSelectDto;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;

import java.util.List;

@SpringBootTest
@Transactional
class SmallCatMapperTest {
    @Autowired
    private SmallCatService smallCatService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testMyBatisQuery() {

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

        List<SmallCatItemPreviewResponse> res = smallCatService.findItemPreviewsByStatus(new SmallCatItemSelectDto(1L, 3L, List.of("시작전")));
        List<SmallCatItemPreviewResponse> res2 = smallCatService.findItemPreviewsByStatus(new SmallCatItemSelectDto(1L, 8L, List.of("시작전", "진행중")));
        Assertions.assertThat(res).isNotNull();
        Assertions.assertThat(res2).isNotNull();
    }
}
