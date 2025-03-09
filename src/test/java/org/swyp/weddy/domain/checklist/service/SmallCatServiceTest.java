package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.SmallCatMapper;
import org.swyp.weddy.domain.checklist.entity.SmallCatItem;
import org.swyp.weddy.domain.checklist.entity.SmallCatItemPreview;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemAddException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemDeleteException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemNotExistsException;
import org.swyp.weddy.domain.checklist.exception.SmallCategoryItemUpdateException;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemMoveDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemSelectDto;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SmallCatServiceTest {
    private SmallCatService smallCatService;
    private SmallCatMapper smallCatMapper;
    private Long checklistId;
    private Long largeCatItemId;
    private Long smallCatItemId;

    @BeforeEach
    void setUp() {
        checklistId = 1L;
        largeCatItemId = 1L;
        smallCatItemId = 1L;

        smallCatMapper = mock(SmallCatMapper.class);
        smallCatService = new SmallCatServiceImpl(smallCatMapper);
    }

    @DisplayName("조회 관련 테스트")
    @Nested
    class SelectTests {

        @DisplayName("소분류 항목 1개 조회 실패시 예외처리")
        @Test
        void findItemFailsWhenNotExists() {
            // given
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(null);

            // when & then
            assertThatThrownBy(() -> smallCatService.findItem(checklistId, largeCatItemId, smallCatItemId))
                    .isInstanceOf(SmallCategoryItemNotExistsException.class);
        }

        @DisplayName("소분류 항목 1개를 조회할 수 있다.")
        @Test
        void findItemSuccess() {
            // given
            SmallCatItem item = SmallCatItem.builder().id(10L).build();
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(item);

            // when
            var result = smallCatService.findItem(checklistId, largeCatItemId, smallCatItemId);

            // then
            assertThat(result).isNotNull();
            assertEquals(result.getId(), item.getId());
        }

        @DisplayName("소분류 항목 Preview 들을 조회할 수 있다.")
        @Test
        void findItemPreviewsSuccess() {
            // given
            SmallCatItemPreview preview = SmallCatItemPreview.builder().id(10L).build();
            when(smallCatMapper.selectItemPreviews(checklistId, largeCatItemId)).thenReturn(List.of(preview));

            // when
            List<SmallCatItemPreviewResponse> result = smallCatService.findItemPreviews(checklistId, largeCatItemId);

            // then
            assertThat(result).isNotNull().hasSize(1);
            assertThat(result).extracting(SmallCatItemPreviewResponse::getId).containsExactly(10L);
        }
    }

    @DisplayName("추가 관련 테스트")
    @Nested
    class AddTests {

        @DisplayName("소분류 항목 1개 추가 실패시 예외처리")
        @Test
        void addItemFailsWhenInsertFails() {
            // given
            when(smallCatMapper.insertItem(any(SmallCatItem.class))).thenReturn(0L);
            SmallCatItemDto dto = SmallCatItemDto.builder().build();

            // when & then
            assertThatThrownBy(() -> smallCatService.addItem(dto))
                    .isInstanceOf(SmallCategoryItemAddException.class);
        }

        @DisplayName("소분류 항목을 추가할 수 있다.")
        @Test
        void addItemSuccess() {
            // given
            SmallCatItemDto dto = SmallCatItemDto.builder()
                    .checklistId(checklistId)
                    .largeCatItemId(largeCatItemId)
                    .id(10L)
                    .build();
            when(smallCatMapper.insertItem(any(SmallCatItem.class))).thenReturn(1L);

            // when
            Long result = smallCatService.addItem(dto);

            // then
            assertEquals(1L, result);
            verify(smallCatMapper).insertItem(any(SmallCatItem.class));
        }
    }

    @DisplayName("수정 관련 테스트")
    @Nested
    class UpdateTests {

        @DisplayName("소분류 항목 1개 수정 실패시 예외처리")
        @Test
        void editItemFailsWhenUpdateFails() {
            // given
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId))
                    .thenReturn(SmallCatItem.builder().build());
            when(smallCatMapper.updateItem(any(SmallCatItem.class))).thenReturn(0);
            SmallCatItemDto dto = SmallCatItemDto.builder()
                    .checklistId(checklistId)
                    .largeCatItemId(largeCatItemId)
                    .id(smallCatItemId)
                    .build();

            // when & then
            assertThatThrownBy(() -> smallCatService.editItem(dto))
                    .isInstanceOf(SmallCategoryItemUpdateException.class);
        }

        @DisplayName("소분류 항목 1개가 존재할 경우, 변경할 수 있다.")
        @Test
        void editItemSuccess() {
            // given
            SmallCatItemDto dto = SmallCatItemDto.builder()
                    .checklistId(checklistId)
                    .largeCatItemId(largeCatItemId)
                    .id(smallCatItemId)
                    .build();
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(mock(SmallCatItem.class));
            when(smallCatMapper.updateItem(any(SmallCatItem.class))).thenReturn(1);

            // when
            boolean result = smallCatService.editItem(dto);

            // then
            assertEquals(true, result);
            verify(smallCatMapper).updateItem(any(SmallCatItem.class));
        }
    }

    @DisplayName("삭제 관련 테스트")
    @Nested
    class DeleteTests {

        @DisplayName("소분류 항목 1개 삭제 실패시 예외처리")
        @Test
        void deleteItemFailsWhenDeleteFails() {
            // given
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId))
                    .thenReturn(SmallCatItem.builder().build());
            when(smallCatMapper.deleteItem(largeCatItemId, smallCatItemId)).thenReturn(0);

            // when & then
            assertThatThrownBy(() -> smallCatService.deleteItem(checklistId, largeCatItemId, smallCatItemId))
                    .isInstanceOf(SmallCategoryItemDeleteException.class);
        }

        @DisplayName("소분류 항목 1개가 존재할 경우, 삭제할 수 있다.")
        @Test
        void deleteItemSuccess() {
            // given
            when(smallCatMapper.selectItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(mock(SmallCatItem.class));
            when(smallCatMapper.deleteItem(largeCatItemId, smallCatItemId)).thenReturn(1);

            // when
            boolean result = smallCatService.deleteItem(checklistId, largeCatItemId, smallCatItemId);

            // then
            assertEquals(true, result);
            verify(smallCatMapper).deleteItem(largeCatItemId, smallCatItemId);
        }

        @DisplayName("소분류 항목 여러개 삭제 실패시 예외처리")
        @Test
        void deleteAllFailsWhenDeleteFails() {
            // given
            when(smallCatMapper.selectItemPreviews(checklistId, largeCatItemId))
                    .thenReturn(List.of(SmallCatItemPreview.builder().build()));
            when(smallCatMapper.deleteAllItems(checklistId, largeCatItemId)).thenReturn(0);

            // when & then
            assertThatThrownBy(() -> smallCatService.deleteAll(checklistId, largeCatItemId))
                    .isInstanceOf(SmallCategoryItemDeleteException.class);
        }

        @DisplayName("소분류 항목들이 존재할 경우, 전체 삭제할 수 있다.")
        @Test
        void deleteAllSuccess() {
            // given
            SmallCatItemPreview preview = SmallCatItemPreview.builder().id(10L).build();
            when(smallCatMapper.selectItemPreviews(checklistId, largeCatItemId)).thenReturn(List.of(preview));
            when(smallCatMapper.deleteAllItems(checklistId, largeCatItemId)).thenReturn(1);

            // when
            boolean result = smallCatService.deleteAll(checklistId, largeCatItemId);

            // then
            assertEquals(true, result);
            verify(smallCatMapper).deleteAllItems(checklistId, largeCatItemId);
        }

        @DisplayName("삭제대상 없을 시 true 반환")
        @Test
        void deleteAllSuccessWhenEmpty() {
            // given
            when(smallCatMapper.selectItemPreviews(checklistId, largeCatItemId)).thenReturn(List.of());

            // when
            boolean result = smallCatService.deleteAll(checklistId, largeCatItemId);

            // then
            assertThat(result).isTrue();
        }
    }

    @DisplayName("항목 이동 관련 테스트")
    @Nested
    class MoveTests {

        @DisplayName("항목 이동 대상 없을 시 예외처리")
        @Test
        void moveItemFailsWhenNoTargets() {
            // given
            SmallCatItemMoveDto dto = mock(SmallCatItemMoveDto.class);
            when(dto.getSmallCatItemIds()).thenReturn(List.of());

            // when & then
            assertThatThrownBy(() -> smallCatService.moveItem(dto))
                    .isInstanceOf(SmallCategoryItemNotExistsException.class);
        }

        @DisplayName("이동 대상이 있을 시 이동 성공")
        @Test
        void moveItemSuccess() {
            // given
            SmallCatItemMoveDto dto = SmallCatItemMoveDto.builder()
                    .checklistId(checklistId)
                    .largeCatItemId(largeCatItemId)
                    .smallCatItemIds(List.of(smallCatItemId))
                    .build();
            when(smallCatMapper.moveItem(any(SmallCatItem.class))).thenReturn(1);

            // when
            boolean result = smallCatService.moveItem(dto);

            // then
            assertThat(result).isTrue();
            verify(smallCatMapper, times(1)).moveItem(any(SmallCatItem.class));
        }
    }

    @DisplayName("필터링 관련 테스트")
    @Nested
    class FilteringTests {
        @DisplayName("진행 상황을 기준으로 소분류 항목을 필터링할 수 있다")
        @Test
        void findItemPreviewsByStatusTest() {
            List<String> itemStatus = List.of("시작전");
            SmallCatItemSelectDto dto = new SmallCatItemSelectDto(checklistId, largeCatItemId, itemStatus, Collections.emptyList());

            when(smallCatMapper.selectItemPreviewsBy(dto)).thenReturn(List.of(
                    mock(SmallCatItemPreview.class),
                    mock(SmallCatItemPreview.class)
            ));

            assertThat(smallCatService.findItemPreviewsBy(dto)).isNotNull();
        }

        @DisplayName("여러 진행 상황을 기준으로 소분류 항목을 필터링할 수 있다")
        @Test
        void findItemPreviewsByTwoStatusTest() {
            List<String> itemStatus = List.of("시작전", "진행중");
            SmallCatItemSelectDto dto = new SmallCatItemSelectDto(checklistId, largeCatItemId, itemStatus, Collections.emptyList());

            when(smallCatMapper.selectItemPreviewsBy(dto)).thenReturn(List.of(
                    mock(SmallCatItemPreview.class),
                    mock(SmallCatItemPreview.class)
            ));

            assertThat(smallCatService.findItemPreviewsBy(dto)).isNotNull();
        }


        @DisplayName("담당자를 기준으로 소분류 항목을 필터링할 수 있다")
        @Test
        void findItemPreviewsByAssigneeTest() {
            List<String> itemAssignee = List.of("신랑");
            SmallCatItemSelectDto dto = new SmallCatItemSelectDto(checklistId, largeCatItemId, Collections.emptyList(), itemAssignee);

            when(smallCatMapper.selectItemPreviewsBy(dto)).thenReturn(List.of(
                    mock(SmallCatItemPreview.class),
                    mock(SmallCatItemPreview.class)
            ));

            assertThat(smallCatService.findItemPreviewsBy(dto)).isNotNull();
        }


        @DisplayName("여러 담당자를 기준으로 소분류 항목을 필터링할 수 있다")
        @Test
        void findItemPreviewsByTwoAssigneesTest() {
            List<String> itemAssignee = List.of("신랑", "신부");
            SmallCatItemSelectDto dto = new SmallCatItemSelectDto(checklistId, largeCatItemId, Collections.emptyList(), itemAssignee);

            when(smallCatMapper.selectItemPreviewsBy(dto)).thenReturn(List.of(
                    mock(SmallCatItemPreview.class),
                    mock(SmallCatItemPreview.class)
            ));

            assertThat(smallCatService.findItemPreviewsBy(dto)).isNotNull();
        }
    }
}