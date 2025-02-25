package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemUpdateException;
import org.swyp.weddy.domain.checklist.service.dto.*;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("findAllItems()")
    @Nested
    class FindAllItemsTest {
        @DisplayName("모든 대분류 항목 읽어오기 요청을 받을 수 있다")
        @Test
        public void receive_all_items_message() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            largeCatService.findAllItems(1L);
        }

        @DisplayName("모든 대분류 항목을 읽어올 수 있다")
        @Test
        public void find_all_items() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            List<LargeCatItemResponse> allItems = largeCatService.findAllItems(1L);
            assertThat(allItems).isNotNull();
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

    @DisplayName("deleteItemWithSmallItems()")
    @Nested
    class DeleteItemWithSmallItemsTest {
        @DisplayName("대분류 항목에 연결된 소분류 항목(들)을 같이 삭제한다")
        @Test
        public void return_value_has_small_cat_items() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            Long deletedItemId = largeCatService.deleteItemWithSmallItems(new LargeCatItemDeleteDto(1L, 1L));
            assertThat(deletedItemId).isNotNull();
        }
    }

    @DisplayName("moveItem()")
    @Nested
    class MoveItem {
        @DisplayName("대분류 항목을 이동할 수 있다.")
        @Test
        public void move_large_cat_item() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());
            largeCatService.moveItem(new LargeCatItemMoveDto(1L, new ArrayList<Long>(List.of(1L))));
        }

        @DisplayName("이동할 순서 리스트가 없을 시 예외처리")
        @Test
        public void throw_exception_if_seqeunce_not_exists() {
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(new FakeLargeCatMapper(), new FakeSmallCatService());

            assertThatThrownBy(() -> {
                largeCatService.moveItem(new LargeCatItemMoveDto(1L, new ArrayList<Long>(List.of())));
            }).isInstanceOf(LargeCatItemNotExistsException.class);

        }

        @DisplayName("이동 실패 시 예외처리")
        @Test
        public void throw_exception_if_updated_row_zero() {
            FakeLargeCatMapper fakeMapper = new FakeLargeCatMapper() {
                @Override
                public int updateItemSequence(LargeCatItem item) {
                    return 0;
                }
            };
            LargeCatServiceImpl largeCatService = new LargeCatServiceImpl(fakeMapper, new FakeSmallCatService());

            assertThatThrownBy(() -> {
                largeCatService.moveItem(new LargeCatItemMoveDto(1L, new ArrayList<Long>(List.of(1L))));
            }).isInstanceOf(LargeCatItemUpdateException.class);
        }
    }

    private static class FakeSmallCatService implements SmallCatService {
        @Override
        public List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId) {
            if (largeCatItemId != 1L) {
                return null;
            }

            return List.of(
                    new SmallCatItemPreviewResponse(1L, 1L, "t1", new Date(), "세훈", "진행중"),
                    new SmallCatItemPreviewResponse(2L, 1L, "t2", new Date(), "세순", "완료"),
                    new SmallCatItemPreviewResponse(3L, 1L, "t3", new Date(), "세준", "진행중")
            );
        }

        @Override
        public SmallCatItemResponse findItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
            return null;
        }

        @Override
        public Long addItem(SmallCatItemDto dto) {
            return 0L;
        }

        @Override
        public boolean editItem(SmallCatItemDto dto) {
            return false;
        }

        @Override
        public boolean deleteItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
            return false;
        }

        @Override
        public boolean deleteAll(Long checklistId, Long largeCatItemId) {
            return false;
        }

        @Override
        public boolean moveItem(SmallCatItemMoveDto dto) {
            return false;
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
        public List<LargeCatItem> selectAllItems(Long checklistId) {
            return List.of(
                    new LargeCatItem(1L, 1L, "test", null, null, Boolean.FALSE),
                    new LargeCatItem(2L, 1L, "test2", null, null, Boolean.FALSE)
            );
        }

        @Override
        public Long insertItem(LargeCatItem item) {
            return 1L;
        }

        @Override
        public Long updateItem(LargeCatItem item) {
            return 1L;
        }

        @Override
        public Long deleteItem(LargeCatItem item) {
            return 0L;
        }

        @Override
        public int updateItemSequence(LargeCatItem item) {
            return 1;
        }

    }
}
