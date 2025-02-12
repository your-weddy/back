package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class LargeCatServiceTest {

    @DisplayName("findItem()")
    @Nested
    class findItemTest {
        @DisplayName("대분류 항목 하나를 가져올 수 있다")
        @Test
        public void find_one_large_cat_item() {
            LargeCatService largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper());
            LargeCatItemResponse item = largeCatService.findItem(1L, 1L);
            assertThat(item).isNotNull();
        }

        @DisplayName("주어진 대분류 아이디에 등록된 항목이 없을 경우를 처리할 수 있다")
        @Test
        public void no_large_cat_item_for_given_id() {
            LargeCatService largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper());
            Assertions.assertThrows(LargeCatItemNotExistsException.class, () ->
                    largeCatService.findItem(-1L, 1L)
            );
        }
    }

    @DisplayName("assignItem()")
    @Nested
    class assignItemTest {
        @DisplayName("대분류 항목 하나를 추가할 수 있다")
        @Test
        public void assign_one_large_cat_item() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper());
            Long id = largeCatService.addItem(new LargeCatItemAssignDto(1L, "test"));
            assertThat(id).isNotNull();
        }

        @DisplayName("dto를 mapper에 보낼 형태로 변환할 수 있다")
        @Test
        public void convert_dto_to_entity() {
            LargeCatItemAssignDto dto = new LargeCatItemAssignDto(1L, "test");
            LargeCatItem item = LargeCatItem.from(dto);
            LargeCatItem expected = new LargeCatItem(
                    1L,
                    "test",
                    new Timestamp(System.currentTimeMillis()),
                    null,
                    Boolean.FALSE
            );
            assertThat(item.getChecklistId()).isEqualTo(expected.getChecklistId());
            assertThat(item.getTitle()).isEqualTo(expected.getTitle());
        }
    }

    private static class FakeLargeCatMapper implements LargeCatMapper {
        @Override
        public LargeCatItem selectItem(Long checkListId, Long id) {
            if (checkListId == -1L) {
                return null;
            }

            return new LargeCatItem(1L,
                    1L,
                    "test",
                    null,
                    null,
                    Boolean.FALSE);
        }

        @Override
        public Long insertItem(LargeCatItem item) {
            return 1L;
        }
    }
}
