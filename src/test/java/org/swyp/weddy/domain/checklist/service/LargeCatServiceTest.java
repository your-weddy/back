package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.smallcategory.service.FakeSmallCatService;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class LargeCatServiceTest {

    @DisplayName("findItem()")
    @Nested
    class FindItemTest {
        @DisplayName("대분류 항목 하나를 가져올 수 있다")
        @Test
        public void find_one_large_cat_item() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            LargeCatItemResponse item = largeCatService.findItem(1L, 1L);
            assertThat(item).isNotNull();
        }

        @DisplayName("주어진 대분류 아이디에 등록된 항목이 없을 경우를 처리할 수 있다")
        @Test
        public void no_large_cat_item_for_given_id() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            Assertions.assertThrows(LargeCatItemNotExistsException.class, () ->
                    largeCatService.findItem(-1L, 1L)
            );
        }
    }

    @DisplayName("findItemWithSmallItems()")
    @Nested
    class FindItemWithSmallItemsTest {
        @DisplayName("대분류 항목에 연결된 소분류 항목(들)을 같이 가져온다")
        @Test
        public void return_value_has_small_cat_items() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            LargeCatItemResponse item = largeCatService.findItemWithSmallItems(1L, 1L);
            assertThat(item.getSmallCatItems()).isNotNull();
        }

        @DisplayName("대분류 항목에 연결된 소분류 항목이 없는 경우를 처리할 수 있다")
        @Test
        public void return_value_has_no_small_cat_items() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            LargeCatItemResponse item = largeCatService.findItemWithSmallItems(1L, 2L);
            assertThat(item.getSmallCatItems()).isEmpty();
        }
    }

    @DisplayName("assignItem()")
    @Nested
    class AssignItemTest {
        @DisplayName("대분류 항목 하나를 추가할 수 있다")
        @Test
        public void assign_one_large_cat_item() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            largeCatService.addItem(new LargeCatItemAssignDto(1L, "test"));
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

    @DisplayName("editItem()")
    @Nested
    class EditItemTest {
        @DisplayName("대분류 항목 하나를 수정할 수 있다")
        @Test
        public void edit_one_large_cat_item() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            Long editItemId = largeCatService.editItem(new LargeCatItemEditDto(1L, 1L, "revised_test"));
            assertThat(editItemId).isNotNull();
        }
    }

    @DisplayName("deleteItem()")
    @Nested
    class DeleteItemTest {
        @DisplayName("대분류 항목 삭제 요청을 받을 수 있다")
        @Test
        public void receive_delete_one_large_cat_item_message() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            largeCatService.deleteItem(new LargeCatItemDeleteDto(1L, 1L));
        }

        @DisplayName("대분류 항목을 삭제할 수 있다")
        @Test
        public void delete_one_large_cat_item() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            Long deletedItemId = largeCatService.deleteItem(new LargeCatItemDeleteDto(1L, 1L));
            assertThat(deletedItemId).isNotNull();
        }
    }

    private static class FakeLargeCatMapper implements LargeCatMapper {
        @Override
        public LargeCatItem selectItem(Long checklistId, Long id) {
            if (checklistId == -1L) {
                return null;
            }

            if (id == 2L) {
                return new LargeCatItem(2L,
                        1L,
                        "test",
                        null,
                        null,
                        Boolean.FALSE);
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

        @Override
        public Long updateItem(LargeCatItem item) {
            return 1L;
        }
    }
}
