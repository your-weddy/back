package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemMoveDto;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.util.List;

public interface SmallCatService {

    List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId);
    SmallCatItemResponse findItem(Long checklistId, Long largeCatItemId, Long smallCatItemId);

    Long addItem(SmallCatItemDto dto);
    boolean editItem(SmallCatItemDto dto);
    boolean deleteItem(Long checklistId, Long largeCatItemId, Long smallCatItemId);
    boolean deleteAll(Long checklistId, Long largeCatItemId);
    boolean moveItem(SmallCatItemMoveDto dto);
}
