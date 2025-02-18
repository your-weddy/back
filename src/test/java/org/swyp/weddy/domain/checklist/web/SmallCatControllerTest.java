package org.swyp.weddy.domain.checklist.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.swyp.weddy.domain.checklist.service.SmallCatService;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPatchRequest;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SmallCatControllerTest {

    Long checklistId;
    Long largeCatItemId;
    Long smallCatItemId;
    SmallCatController smallCatController;
    SmallCatService smallCatService;

    @BeforeEach
    void setUp() {
        checklistId = 1L;
        largeCatItemId = 1L;
        smallCatItemId = 1L;

        smallCatService = mock(SmallCatService.class);
        smallCatController = new SmallCatController(smallCatService);
    }

    @Nested
    class GetItemPreviewsTest {
        @DisplayName("소분류 항목 Preview 들을 반환할 수 있다.")
        @Test
        void testGetSmallCatItemList() {
            //given
            List<SmallCatItemPreviewResponse> mockResponse = List.of(new SmallCatItemPreviewResponse());
            when(smallCatService.findItemPreviews(checklistId, largeCatItemId)).thenReturn(mockResponse);

            //when
            var result = smallCatController.getSmallCatItemList(checklistId, largeCatItemId);

            //then
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(mockResponse, result.getBody());
        }
    }

    @Nested
    class GetItemTest {
        @DisplayName("소분류 항목 1개를 반환할 수 있다.")
        @Test
        void testGetSmallCatItem() {
            //given
            SmallCatItemResponse mockResponse = new SmallCatItemResponse();
            when(smallCatService.findItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(mockResponse);

            //when
            var result = smallCatController.getSmallCatItem(checklistId, largeCatItemId, smallCatItemId);

            //then
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(mockResponse, result.getBody());
        }
    }

    @Nested
    class PostItemTest {
        @DisplayName("소분류 항목 추가 결과를 반환할 수 있다.")
        @Test
        void testPostItem() {
            //given
            Long expectedItemId = 1L;
            SmallCatItemPostRequest request = new SmallCatItemPostRequest();
            when(smallCatService.addItem(any(SmallCatItemDto.class))).thenReturn(expectedItemId);

            //when
            var result = smallCatController.postItem(request);

            //then
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(expectedItemId, result.getBody());
        }

        @DisplayName("request 값이 Service객체로 올바르게 전달되는지 검증한다.")
        @Test
        void testPostItemArgument() {
            //given
            SmallCatItemPostRequest request = new SmallCatItemPostRequest();
            SmallCatItemDto dto = SmallCatItemDto.from(request);

            //when
            smallCatController.postItem(request);

            //then
            ArgumentCaptor<SmallCatItemDto> captor = ArgumentCaptor.forClass(SmallCatItemDto.class);
            verify(smallCatService).addItem(captor.capture());
            assertEquals(dto.getId(), captor.getValue().getId());
        }
    }

    @Nested
    class PatchItemTest {
        @DisplayName("소분류 항목 변경 결과를 반환할 수 있다.")
        @Test
        void testPatchItem() {
            //given
            SmallCatItemPatchRequest request = new SmallCatItemPatchRequest();
            when(smallCatService.editItem(any(SmallCatItemDto.class))).thenReturn(true);

            //when
            var result = smallCatController.patchItem(request);

            //then
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(true, result.getBody());
        }

        @DisplayName("request 값이 Service객체로 올바르게 전달되는지 검증한다.")
        @Test
        void testPatchItemArgument() {
            //given
            SmallCatItemPatchRequest request = new SmallCatItemPatchRequest();
            SmallCatItemDto dto = SmallCatItemDto.from(request);

            //when
            smallCatController.patchItem(request);

            //then
            ArgumentCaptor<SmallCatItemDto> captor = ArgumentCaptor.forClass(SmallCatItemDto.class);
            verify(smallCatService).editItem(captor.capture());
            assertEquals(dto.getId(), captor.getValue().getId());
        }

    }

    @Nested
    class DeleteItemTest {
        @DisplayName("소분류 항목 삭제 결과를 반환할 수 있다.")
        @Test
        void testDeleteItem() {
            //given
            when(smallCatService.deleteItem(checklistId, largeCatItemId, smallCatItemId)).thenReturn(true);

            //when
            var result = smallCatController.deleteItem(checklistId, largeCatItemId, smallCatItemId);

            //then
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(true, result.getBody());
            verify(smallCatService).deleteItem(checklistId, largeCatItemId, smallCatItemId);
        }
    }

    @Nested
    class DeleteAllItemsTest {
        @DisplayName("소분류 항목 전체 삭제 결과를 반환할 수 있다.")
        @Test
        void testDeleteAllItems() {
            //given
            when(smallCatService.deleteAll(checklistId, largeCatItemId)).thenReturn(true);

            //when
            var result = smallCatController.deleteAllItems(1L, 1L);

            //then
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(true, result.getBody());
            verify(smallCatService).deleteAll(checklistId, largeCatItemId);
        }
    }
}
