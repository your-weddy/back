package org.swyp.weddy.domain.checklist.service.dto;

import org.swyp.weddy.domain.checklist.web.request.LargeCatItemEditRequest;

public class LargeCatItemEditDto {
    private Long checklistId;
    private Long id;
    private String newTitle;

    public LargeCatItemEditDto(Long checklistId, Long id, String newTitle) {
        this.checklistId = checklistId;
        this.id = id;
        this.newTitle = newTitle;
    }

    public static LargeCatItemEditDto of(Long checklistId, LargeCatItemEditRequest request) {
        return new LargeCatItemEditDto(
                checklistId,
                Long.valueOf(request.getId()),
                request.getEditedTitle()
        );
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
