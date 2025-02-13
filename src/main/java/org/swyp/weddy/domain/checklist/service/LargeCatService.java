package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

public interface LargeCatService {
    LargeCatItemResponse findItem(Long checklistId, Long id);

    LargeCatItemResponse findItemWithSmallItems(Long checklistId, Long id);

    Long addItem(LargeCatItemAssignDto dto);

    Long editItem(LargeCatItemEditDto dto);
}
