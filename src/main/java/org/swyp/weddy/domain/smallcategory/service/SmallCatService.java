package org.swyp.weddy.domain.smallcategory.service;

import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemResponse;

import java.util.List;

public interface SmallCatService {

    List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId); //Long checklistId, Long largeCatId);
    SmallCatItemResponse findItem(Long checklistId, Long largeCatItemId, Long smallCatItemId);

    Long addItem(SmallCatItemDto dto);//Long checklistId, Long largeCatId
    boolean editItem(SmallCatItemDto dto); //Long checklistId, Long largeCatId, Long smallCatId);
    boolean deleteItem(Long checklistId, Long largeCatItemId, Long smallCatItemId); //Long checklistId, Long largeCatId, Long smallCatId);
    boolean deleteAll(Long checklistId, Long largeCatItemId);  //Long checklistId, Long largeCatId,
}
