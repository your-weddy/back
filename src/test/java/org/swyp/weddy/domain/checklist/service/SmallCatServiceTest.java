package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.swyp.weddy.domain.checklist.dao.SmallCatMapper;
import org.swyp.weddy.domain.checklist.entity.SmallCatItem;
import org.swyp.weddy.domain.checklist.entity.SmallCatItemPreview;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemAddException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemDeleteException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemNotExistsException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemUpdateException;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SmallCatServiceTest {

    private SmallCatService smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper());

    @DisplayName("예외처리 테스트")
    @Nested
    class ExceptionTest {
        @DisplayName("소분류 항목 1개 조회 실패시 예외처리")
        @Test
        void findItem_Exception_Test() {
            assertThatThrownBy(() -> {
                smallCatServiceImpl.findItem(1L, 1L, 1L);
            }).isInstanceOf(SmallCategoryItemNotExistsException.class);
        }

        @DisplayName("소분류 항목 1개 추가 실패시 예외처리")
        @Test
        void addItem_Exception_Test() {
            assertThatThrownBy(() -> {
                smallCatServiceImpl.addItem(new SmallCatItemDto(null, null, null, null, null, null, null, null, null));
            }).isInstanceOf(SmallCategoryItemAddException.class);
        }

        @DisplayName("소분류 항목 1개 추가 실패시 예외처리")
        @Test
        void editItem_Exception_Test() {
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper() {
                @Override
                public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
                    return new SmallCatItem(null, null, null, null, null, null, null, null);
                }
            });
            assertThatThrownBy(() -> {
                smallCatServiceImpl.editItem(new SmallCatItemDto(null, null, null, null, null, null, null, null, null));
            }).isInstanceOf(SmallCategoryItemUpdateException.class);
        }

        @DisplayName("소분류 항목 1개 삭제 실패시 예외처리")
        @Test
        void deleteItem_Exception_Test() {
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper() {
                @Override
                public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
                    return new SmallCatItem(null, null, null, null, null, null, null, null);
                }
            });
            assertThatThrownBy(() -> {
                smallCatServiceImpl.deleteItem(1L, 1L, 1L);
            }).isInstanceOf(SmallCategoryItemDeleteException.class);
        }

        @DisplayName("소분류 항목 여러개 삭제 실패시 예외처리")
        @Test
        void deleteAll_Exception_Test() {
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper() {
                @Override
                public List<SmallCatItemPreview> selectItemPreviews(Long checklistId, Long largeCatItemId) {
                    return List.of(new SmallCatItemPreview(null, null, null, null, null, null));
                }
            });
            assertThatThrownBy(() -> {
                smallCatServiceImpl.deleteAll(1L, 1L);
            }).isInstanceOf(SmallCategoryItemDeleteException.class);
        }

        @DisplayName("삭제대상 없을 시 true 반환")
        @Test
        void deleteAll_Return_True_If_Select_Empty_Test() {
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper());
            assertThat(smallCatServiceImpl.deleteAll(1L, 1L)).isTrue();
        }
    }

    static class FakeMapper implements SmallCatMapper {
        @Override
        public List<SmallCatItemPreview> selectItemPreviews(Long checklistId, Long largeCatItemId) {
            return List.of();
        }

        @Override
        public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
            return null;
        }

        @Override
        public Long insertItem(SmallCatItem smallCatItem) {
            return 0L;
        }

        @Override
        public int updateItem(SmallCatItem smallCatItem) {
            return 0;
        }

        @Override
        public int deleteItem(Long largeCatItemId, Long smallCatItemId) {
            return 0;
        }

        @Override
        public int deleteAllItems(Long checklistId, Long largeCatItemId) {
            return 0;
        }
    }
}