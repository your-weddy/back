package org.swyp.weddy.domain.checklist.service.dto;

public class FilterByStatusDto {
    private Long checklistId;
    private String itemStatus;

    public FilterByStatusDto(Long checklistId, String itemStatus) {
        this.checklistId = checklistId;
        this.itemStatus = itemStatus;
    }
}
