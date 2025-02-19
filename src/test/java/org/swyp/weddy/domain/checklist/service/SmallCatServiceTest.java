package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swyp.weddy.domain.checklist.dao.SmallCatMapper;
import org.swyp.weddy.domain.checklist.entity.SmallCatItem;
import org.swyp.weddy.domain.checklist.entity.SmallCatItemPreview;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemAddException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemDeleteException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemNotExistsException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemUpdateException;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SmallCatServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SmallCatServiceTest.class);

    @DisplayName("예외처리 테스트")
    @Nested
    class ExceptionTest {
        private SmallCatService smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper());

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
            //given
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper() {
                @Override
                public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
                    return new SmallCatItem(null, null, null, null, null, null, null, null);
                }
            });

            //when, then
            assertThatThrownBy(() -> {
                smallCatServiceImpl.editItem(new SmallCatItemDto(null, null, null, null, null, null, null, null, null));
            }).isInstanceOf(SmallCategoryItemUpdateException.class);
        }

        @DisplayName("소분류 항목 1개 삭제 실패시 예외처리")
        @Test
        void deleteItem_Exception_Test() {
            //given
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper() {
                @Override
                public SmallCatItem selectItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {
                    return new SmallCatItem(null, null, null, null, null, null, null, null);
                }
            });

            //when, then
            assertThatThrownBy(() -> {
                smallCatServiceImpl.deleteItem(1L, 1L, 1L);
            }).isInstanceOf(SmallCategoryItemDeleteException.class);
        }

        @DisplayName("소분류 항목 여러개 삭제 실패시 예외처리")
        @Test
        void deleteAll_Exception_Test() {
            //given
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper() {
                @Override
                public List<SmallCatItemPreview> selectItemPreviews(Long checklistId, Long largeCatItemId) {
                    return List.of(new SmallCatItemPreview(null, null, null, null, null, null));
                }
            });

            //when, then
            assertThatThrownBy(() -> {
                smallCatServiceImpl.deleteAll(1L, 1L);
            }).isInstanceOf(SmallCategoryItemDeleteException.class);
        }

        @DisplayName("삭제대상 없을 시 true 반환")
        @Test
        void deleteAll_Return_True_If_Select_Empty_Test() {
            //given
            smallCatServiceImpl = new SmallCatServiceImpl(new FakeMapper());

            //when, then
            assertThat(smallCatServiceImpl.deleteAll(1L, 1L)).isTrue();
        }
    }

    @DisplayName("서비스 로직 테스트")
    @Nested
    class ServiceTest {
        Long checklistId;
        Long largeCatItemId;
        Long smallCatItemId;

        private SmallCatService smallCatService;
        private SmallCatMapper smallCatMapper;

        @BeforeEach
        void setUp(){
            checklistId = 1L;
            largeCatItemId = 1L;
            smallCatItemId = 1L;

            smallCatMapper = mock(SmallCatMapper.class);
            smallCatService = new SmallCatServiceImpl(smallCatMapper);
        }

        @DisplayName("소분류 항목 Preview 들을 조회할 수 있다.")
        @Test
        void getItemPreviewsTest(){
            //given
            SmallCatItemPreview preview = SmallCatItemPreview.builder()
                    .id(10L)
                    .build();
            when(smallCatMapper.selectItemPreviews(checklistId, largeCatItemId)).thenReturn(List.of(preview));

            //when
            List<SmallCatItemPreviewResponse> result = smallCatService.findItemPreviews(checklistId, largeCatItemId);

            //then
            assertThat(result).isNotNull();
            assertThat(result).hasSize(1);
            assertThat(result).extracting(SmallCatItemPreviewResponse::getId)
                    .containsExactly(10L);
        }


        @DisplayName("소분류 항목 1개를 조회할 수 있다.")
        @Test
        void getSmallCatItemTest() {
            //given
            SmallCatItem item = SmallCatItem.builder()
                    .id(10L)
                    .build();
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(item);

            //when
            var result = smallCatService.findItem(checklistId, largeCatItemId, smallCatItemId);

            //then
            assertThat(result).isNotNull();
            assertEquals(result.getId(), item.getId());
        }

        @DisplayName("소분류 항목을 추가할 수 있다.")
        @Test
        void addItemTest() {
            //given
            Long expectedResult = 1L;
            SmallCatItemDto dto = SmallCatItemDto.builder()
                    .id(10L)
                    .build();
            when(smallCatMapper.insertItem(any(SmallCatItem.class))).thenReturn(expectedResult);

            //when
            Long result = smallCatService.addItem(dto);

            //then
            assertEquals(expectedResult , result);
            verify(smallCatMapper).insertItem(any(SmallCatItem.class));

        }

        @DisplayName("소분류 항목 1개가 존재할 경우, 변경할 수 있다.")
        @Test
        void editItemTest() {
            //given
            SmallCatItemDto dto = SmallCatItemDto.builder()
                    .id(checklistId)
                    .id(largeCatItemId)
                    .id(smallCatItemId)
                    .build();
            boolean expectedResult = true;
            when(smallCatMapper.selectItem(dto.getChecklistId(), dto.getLargeCatItemId(), dto.getId())).thenReturn(mock(SmallCatItem.class));
            when(smallCatMapper.updateItem(any(SmallCatItem.class))).thenReturn(1);

            //when
            boolean result = smallCatService.editItem(dto);

            //then
            assertEquals(expectedResult , result);
            verify(smallCatMapper).updateItem(any(SmallCatItem.class));
        }

        @DisplayName("소분류 항목 1개가 존재할 경우, 삭제할 수 있다.")
        @Test
        void deleteItemTest() {
            //given
            boolean expectedResult = true;
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(mock(SmallCatItem.class));
            when(smallCatMapper.deleteItem(largeCatItemId, smallCatItemId)).thenReturn(1);

            //when
            boolean result = smallCatService.deleteItem(checklistId, largeCatItemId, smallCatItemId);

            //then
            assertEquals(expectedResult , result);
            verify(smallCatMapper).deleteItem(largeCatItemId, smallCatItemId);

        }

        @DisplayName("소분류 항목들이 존재할 경우, 전체 삭제할 수 있다.")
        @Test
        void deleteAllItemsTest() {
            //given
            boolean expectedResult = true;
            SmallCatItemPreview preview = SmallCatItemPreview.builder()
                    .id(10L)
                    .build();
            when(smallCatMapper.selectItemPreviews(checklistId, largeCatItemId)).thenReturn(List.of(preview));
            when(smallCatMapper.deleteAllItems(checklistId, largeCatItemId)).thenReturn(1);

            //when
            boolean result = smallCatService.deleteAll(checklistId, largeCatItemId);

            //then
            assertEquals(expectedResult , result);
            verify(smallCatMapper).deleteAllItems(checklistId, largeCatItemId);

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