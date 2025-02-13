package org.swyp.weddy.domain.checklist.service.dto;

import org.swyp.weddy.domain.checklist.web.request.LargeCatItemDeleteRequest;

public class LargeCatItemDeleteDto {
    private Long checklistId;
    private Long id;

    public LargeCatItemDeleteDto(Long checklistId, Long id) {
        this.checklistId = checklistId;
        this.id = id;
    }

    public static LargeCatItemDeleteDto of(Long checklistId, LargeCatItemDeleteRequest request) {
        return new LargeCatItemDeleteDto(
                checklistId,
                Long.valueOf(request.getId())
        );
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public Long getId() {
        return id;
    }
}
