package org.swyp.weddy.domain.checklist.service.dto;

public class SmallCatItemSelectDto {
    private Long checklistId;
    private Long largeCatItemId;
    private String itemStatus;

    public SmallCatItemSelectDto(Long checklistId, Long largeCatItemId, String itemStatus) {
        this.checklistId = checklistId;
        this.largeCatItemId = largeCatItemId;
        this.itemStatus = itemStatus;
    }

    public static SmallCatItemSelectDto from(FilterByStatusDto dto, Long id) {
        return new SmallCatItemSelectDto(
                dto.getChecklistId(),
                id,
                dto.getItemStatus()
        );
    }
}
