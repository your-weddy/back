package org.swyp.weddy.domain.checklist.service.dto;

public class LargeCatItemAssignDto {
    private Long checklistId;
    private String title;

    public LargeCatItemAssignDto(Long checklistId, String title) {
        this.checklistId = checklistId;
        this.title = title;
    }
}
