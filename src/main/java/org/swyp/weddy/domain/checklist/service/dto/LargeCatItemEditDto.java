package org.swyp.weddy.domain.checklist.service.dto;

public class LargeCatItemEditDto {
    private Long checklistId;
    private Long id;
    private String newTitle;

    public LargeCatItemEditDto(Long checklistId, Long id, String newTitle) {
        this.checklistId = checklistId;
        this.id = id;
        this.newTitle = newTitle;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public Long getId() {
        return id;
    }

    public String getNewTitle() {
        return newTitle;
    }
}
