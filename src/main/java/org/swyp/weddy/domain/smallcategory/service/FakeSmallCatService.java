package org.swyp.weddy.domain.smallcategory.service;

import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemResponse;

import java.util.Date;
import java.util.List;

public class FakeSmallCatService implements SmallCatService {
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
}
