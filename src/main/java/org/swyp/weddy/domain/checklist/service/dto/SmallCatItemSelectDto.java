package org.swyp.weddy.domain.checklist.service.dto;

public class SmallCatItemSelectDto {
    private Long checklistId;
    private String itemStatus;
    private Long largeCatItemId;

    public SmallCatItemSelectDto(Long checklistId, String itemStatus, Long largeCatItemId) {
        this.checklistId = checklistId;
        this.itemStatus = itemStatus;
        this.largeCatItemId = largeCatItemId;
    }

    public static SmallCatItemSelectDto from(FilterByStatusDto dto, Long id) {
        return null;
    }
}
