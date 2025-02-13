package org.swyp.weddy.domain.smallcategory.service;

import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;

import java.util.List;

public interface SmallCatService {

    List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId); //Long checklistId, Long largeCatId);
}
