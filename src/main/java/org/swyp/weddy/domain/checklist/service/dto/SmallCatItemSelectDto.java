package org.swyp.weddy.domain.checklist.service.dto;

import java.util.List;

public class SmallCatItemSelectDto {
    private Long checklistId;
    private Long largeCatItemId;
    private List<String> itemStatus;

    public SmallCatItemSelectDto(Long checklistId, Long largeCatItemId, List<String> itemStatus) {
        this.checklistId = checklistId;
        this.largeCatItemId = largeCatItemId;
        this.itemStatus = itemStatus;
    }

    public static SmallCatItemSelectDto of(FilteringDto dto, Long id) {
        return new SmallCatItemSelectDto(
                dto.getChecklistId(),
                id,
                dto.getItemStatusList()
        );
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public Long getLargeCatItemId() {
        return largeCatItemId;
    }

    public List<String> getItemStatus() {
        return itemStatus;
    }
}
