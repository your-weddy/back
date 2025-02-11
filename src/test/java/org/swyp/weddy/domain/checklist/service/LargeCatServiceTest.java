package org.swyp.weddy.domain.checklist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
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

    private static class FakeLargeCatService implements LargeCatService {
        private final LargeCatMapper mapper;

        public FakeLargeCatService(LargeCatMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public LargeCatItemResponse findItem(Long memberId, Long id) {
            LargeCatItem largeCatItem = mapper.selectItem(memberId, id);
            return LargeCatItemResponse.from(largeCatItem);
        }
    }

    private static class FakeLargeCatMapper implements LargeCatMapper {
        @Override
        public LargeCatItem selectItem(Long checkListId, Long id) {
            return new LargeCatItem();
        }
    }
}
