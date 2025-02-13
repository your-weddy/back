package org.swyp.weddy.domain.checklist.service.dto;

public class LargeCatItemDeleteDto {
    private Long checklistId;
    private Long id;

    public LargeCatItemDeleteDto(Long checklistId, Long id) {
        this.checklistId = checklistId;
        this.id = id;
    }
}
