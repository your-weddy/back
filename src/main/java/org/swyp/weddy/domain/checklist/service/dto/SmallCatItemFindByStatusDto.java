package org.swyp.weddy.domain.checklist.service.dto;

public class SmallCatItemFindByStatusDto {
    private Long checklistId;
    private String itemStatus;
    private Long largeCatItemId;

    public SmallCatItemFindByStatusDto(Long checklistId, String itemStatus, Long largeCatItemId) {
        this.checklistId = checklistId;
        this.itemStatus = itemStatus;
        this.largeCatItemId = largeCatItemId;
    }

    public static SmallCatItemFindByStatusDto from(FilterByStatusDto dto, Long id) {
        return null;
    }
}
