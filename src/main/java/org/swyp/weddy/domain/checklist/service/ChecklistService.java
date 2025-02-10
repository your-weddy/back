package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

public interface ChecklistService {
    int assignChecklist(ChecklistDto dto);

    boolean hasChecklist(ChecklistDto dto);
}
