package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

public interface LargeCatService {
    LargeCatItemResponse findItem(Long checklistId, Long id);

    LargeCatItemResponse findItemWithSmallItems(Long checklistId, Long id);

    Long addItem(LargeCatItemAssignDto dto);

    Long editItem(LargeCatItemEditDto dto);

    Long deleteItem(LargeCatItemDeleteDto dto);

    List<LargeCatItemResponse> findAllItems(Long checklistId);
}
