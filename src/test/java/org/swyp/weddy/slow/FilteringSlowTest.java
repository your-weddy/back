package org.swyp.weddy.slow;

import org.junit.jupiter.api.Assertions;
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
import org.swyp.weddy.domain.checklist.service.SmallCatService;
import org.swyp.weddy.domain.checklist.service.dto.FilteringDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemSelectDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("slow")
@Transactional
@SpringBootTest
public class FilteringSlowTest {
    @Autowired
    private FilteringService filteringService;
    @Autowired
    private SmallCatService smallCatService;
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

        assertThat(noResult).isEmpty();
    }

    @DisplayName("filteringService.filterByStatus() 검색 결과가 없을 때 예외를 반환한다")
    @Test
    void throws_exception_when_no_result_in_filter_by_status() {
        jdbcTemplate.update("""
                update large_category_item
                set is_deleted = true
                """);

        Assertions.assertThrows(LargeCatItemNotExistsException.class, () -> {
            filteringService.filterBy(new FilteringDto(1L, List.of("시작전"), Collections.emptyList()));
        });
    }

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

        List<LargeCatItemResponse> response1 = filteringService.filterBy(new FilteringDto(1L, List.of("시작전"), Collections.emptyList()));
        List<LargeCatItemResponse> response2 = filteringService.filterBy(new FilteringDto(1L, List.of("시작전", "진행중"), Collections.emptyList()));

        assertThat(response1).isNotNull();
        assertThat(response2).isNotNull();
    }

    @DisplayName("진행 상황 기준으로 대분류 항목 필터링 시, 일치하는 소분류 항목이 없는 대분류 항목은 포함하지 않는다")
    @Test
    void ignore_large_cat_items_not_matched() {
        jdbcTemplate.update("""
                update small_category_item
                set status_id = 1
                where title in ('스튜디오 / 스냅 예약' , '드레스투어 일정 예약' , '스튜디오 드레스 선택' , '본식 드레스 선택')
                and small_category_item.large_category_item_id = 3;
                """);

        List<LargeCatItemResponse> filteredItems = filteringService.filterBy(new FilteringDto(1L, List.of("시작전"), Collections.emptyList()));

        var itemsWithEmptySmallItems = filteredItems.stream().filter(
                item -> item.getSmallCatItems().size() == 0
        );

        assertThat(itemsWithEmptySmallItems.count()).isEqualTo(0);
    }

    @DisplayName("진행 상황 기준으로 소분류 항목을 필터링할 수 있다")
    @Test
    void filter_small_cat_items_by_status() {
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

        List<SmallCatItemPreviewResponse> response1 = smallCatService.findItemPreviewsBy(new SmallCatItemSelectDto(1L, 3L, List.of("시작전"), Collections.emptyList()));
        List<SmallCatItemPreviewResponse> response2 = smallCatService.findItemPreviewsBy(new SmallCatItemSelectDto(1L, 8L, List.of("시작전", "진행중"), Collections.emptyList()));

        assertThat(response1).isNotNull();
        assertThat(response2).isNotNull();
    }

    @DisplayName("담당자 기준으로 대분류 항목을 필터링할 수 있다")
    @Test
    void filter_large_cat_items_by_assignee() {
        jdbcTemplate.update("""
                update small_category_item
                set assignee_id = 1
                where title in ('스튜디오 / 스냅 예약' , '드레스투어 일정 예약' , '스튜디오 드레스 선택' , '본식 드레스 선택')
                and small_category_item.large_category_item_id = 3;
                """);

        jdbcTemplate.update("""
                update small_category_item
                set assignee_id = 1
                where title in ('신혼집 계약', '가전/가구 구매')
                and small_category_item.large_category_item_id = 8;
                        """);

        jdbcTemplate.update("""
                update small_category_item
                set assignee_id = 2
                where title in ('침구/생활용품 구매', '주방용품 구매', '신혼집 입주')
                and small_category_item.large_category_item_id = 8;
                """);

        List<LargeCatItemResponse> response1 = filteringService.filterBy(new FilteringDto(1L, Collections.emptyList(), List.of("신랑")));
        List<LargeCatItemResponse> response2 = filteringService.filterBy(new FilteringDto(1L, Collections.emptyList(), List.of("신랑", "신부")));

        assertThat(response1).isNotNull();
        assertThat(response2).isNotNull();
    }

    @DisplayName("담당자, 진행상황 기준으로 대분류 항목을 필터링할 수 있다")
    @Test
    void filter_large_cat_items_by_multiple_conditions() {
        jdbcTemplate.update("""
                update SMALL_CATEGORY_ITEM\s
                set assignee_id = 1
                where large_category_item_id = 1 and title = '결혼식 컨셉 나눔';
                """);
        jdbcTemplate.update("""
                update SMALL_CATEGORY_ITEM\s
                set assignee_id = 2
                where large_category_item_id = 1 and title = '결혼 비용 논의';
                """);
        jdbcTemplate.update("""
                update SMALL_CATEGORY_ITEM\s
                set status_id = 1
                where large_category_item_id = 1 and title = '결혼 비용 논의';
                """);
        jdbcTemplate.update("""
                update SMALL_CATEGORY_ITEM\s
                set status_id = 2
                where large_category_item_id = 1 and title = '양가 부모님 첫인사';
                """);

        List<LargeCatItemResponse> filterByOneAssignee = filteringService.filterBy(
                new FilteringDto(1L, Collections.emptyList(), List.of("신랑"))
        );
        assertThat(
                filterByOneAssignee.get(0).getSmallCatItems().stream().filter(
                        x -> x.getAssigneeName().equals("신랑")
                ).count()
        ).isEqualTo(1);

        List<LargeCatItemResponse> filterByTwoAssignee = filteringService.filterBy(
                new FilteringDto(1L, Collections.emptyList(), List.of("신랑", "신부"))
        );
        assertThat(
                filterByTwoAssignee.get(0).getSmallCatItems().stream().filter(
                        x -> x.getAssigneeName().equals("신랑")
                ).count()
        ).isEqualTo(1);
        assertThat(
                filterByTwoAssignee.get(0).getSmallCatItems().stream().filter(
                        x -> x.getAssigneeName().equals("신부")
                ).count()
        ).isEqualTo(1);

        List<LargeCatItemResponse> filterByOneStatusAndOneAssignee = filteringService.filterBy(new FilteringDto(1L, List.of("시작전"), List.of("신부")));
        assertThat(
                filterByOneStatusAndOneAssignee.get(0).getSmallCatItems().stream().filter(
                        x -> x.getAssigneeName().equals("신부") && x.getStatusName().equals("시작전")
                ).count()
        ).isEqualTo(1);
    }
}
