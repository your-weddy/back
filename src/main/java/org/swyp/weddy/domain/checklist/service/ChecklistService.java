package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.service.dto.ChecklistDdayAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

public interface ChecklistService {
    Long assignChecklist(ChecklistDto dto);

    boolean hasChecklist(ChecklistDto dto);

    ChecklistResponse findChecklist(ChecklistDto dto);

    Long editDday(ChecklistDdayAssignDto dto);
}
