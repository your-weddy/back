package org.swyp.weddy.domain.checklist.service.dto;

import java.util.List;

public class FilterByStatusDto {
    private Long checklistId;
    private List<String> itemStatus;

    public FilterByStatusDto(Long checklistId, List<String> itemStatus) {
        this.checklistId = checklistId;
        this.itemStatus = itemStatus;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public List<String> getItemStatus() {
        return itemStatus;
    }
}
