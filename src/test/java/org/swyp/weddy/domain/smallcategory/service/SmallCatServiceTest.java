package org.swyp.weddy.domain.smallcategory.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.smallcategory.dao.SmallCatItemMapper;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItemPreview;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemAddException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemDeleteException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemNotExistsException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemUpdateException;
import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SmallCatServiceTest {

    private SmallCatService smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper());

    @DisplayName("조회 예외처리 테스트. 소분류 항목 1개 조회 실패시 예외처리")
    @Test
    void findItem_Exception_Test() {
        assertThatThrownBy(()-> {
            smallCatServiceImpl.findItem(1L, 1L,1L);
        }).isInstanceOf(SmallCategoryItemNotExistsException.class);
    }

    @DisplayName("INSERT 예외처리 테스트. 소분류 항목 1개 추가 실패시 예외처리")
    @Test
    void addItem_Exception_Test() {
        assertThatThrownBy(()-> {
            smallCatServiceImpl.addItem(new SmallCatItemDto(null,null,null,null,null,null,null,null,null));
        }).isInstanceOf(SmallCategoryItemAddException.class);
    }

    @DisplayName("UPDATE 예외처리 테스트. 소분류 항목 1개 추가 실패시 예외처리")
    @Test
    void editItem_Exception_Test() {
        smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper(){
            @Override
            public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
                return new SmallCatItem(null,null,null,null,null,null,null,null);
            }
        });
        assertThatThrownBy(()-> {
            smallCatServiceImpl.editItem(new SmallCatItemDto(null,null,null,null,null,null,null,null,null));
        }).isInstanceOf(SmallCategoryItemUpdateException.class);
    }

    @DisplayName("DELETE 예외처리 테스트. 소분류 항목 1개 삭제 실패시 예외처리")
    @Test
    void deleteItem_Exception_Test() {
        smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper(){
            @Override
            public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
                return new SmallCatItem(null,null,null,null,null,null,null,null);
            }
        });
        assertThatThrownBy(()-> {
            smallCatServiceImpl.deleteItem(1L, 1L, 1L);
        }).isInstanceOf(SmallCategoryItemDeleteException.class);
    }

    @DisplayName("DELETE 예외처리 테스트. 소분류 항목 여러개 삭제 실패시 예외처리")
    @Test
    void deleteAll_Exception_Test() {
        smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper(){
            @Override
            public List<SmallCatItemPreview> selectItemPreviews(Long checklistId, Long largeCatItemId) {
                return List.of(new SmallCatItemPreview(null,null,null,null,null,null));
            }
        });
        assertThatThrownBy(()-> {
            smallCatServiceImpl.deleteAll(1L, 1L);
        }).isInstanceOf(SmallCategoryItemDeleteException.class);
    }

    @DisplayName("DELETE ALL 테스트. 삭제대상 없을 시 true 반환")
    @Test
    void deleteAll_Return_True_If_Select_Empty_Test() {
        smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper());
        assertThat(smallCatServiceImpl.deleteAll(1L, 1L)).isTrue();
    }

    static class FakeMapper implements SmallCatItemMapper {
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