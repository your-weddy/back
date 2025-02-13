package org.swyp.weddy.domain.smallcategory.service;

import org.springframework.stereotype.Service;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;

import java.util.Date;
import java.util.List;

@Service
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
}
