package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import static org.assertj.core.api.Assertions.assertThat;

class LargeCatServiceTest {

    @DisplayName("대분류 항목 하나를 가져올 수 있다")
    @Test
    public void find_one_large_cat_item() {
        LargeCatService largeCatService = new FakeLargeCatService(new FakeLargeCatMapper());
        LargeCatItemResponse item = largeCatService.findItem(1L, 1L);
        assertThat(item).isNotNull();
    }

    @DisplayName("주어진 대분류 아이디에 등록된 항목이 없을 경우를 처리할 수 있다")
    @Test
    public void no_large_cat_item_for_given_id() {
        LargeCatService largeCatService = new FakeLargeCatService(new FakeLargeCatMapper());
        Assertions.assertThrows(LargeCatItemNotExistsException.class, () ->
                largeCatService.findItem(-1L, 1L)
        );
    }

    private static class FakeLargeCatService implements LargeCatService {
        private final LargeCatMapper mapper;

        public FakeLargeCatService(LargeCatMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public LargeCatItemResponse findItem(Long checklistId, Long id) {
            LargeCatItem largeCatItem = mapper.selectItem(checklistId, id);
            if (largeCatItem == null) {
                throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
            }
            return LargeCatItemResponse.from(largeCatItem);
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
    }
}
