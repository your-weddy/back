package org.swyp.weddy.domain.checklist.service.dto;

import java.util.List;

public class SmallCatItemSelectDto {
    private Long checklistId;
    private Long largeCatItemId;
    private List<String> itemStatus;
    private List<String> itemAssignee;

    public SmallCatItemSelectDto(Long checklistId, Long largeCatItemId, List<String> itemStatus, List<String> itemAssignee) {
        this.checklistId = checklistId;
        this.largeCatItemId = largeCatItemId;
        this.itemStatus = itemStatus;
        this.itemAssignee = itemAssignee;
    }

    public static SmallCatItemSelectDto of(FilteringDto dto, Long id) {
        return new SmallCatItemSelectDto(
                dto.getChecklistId(),
                id,
                dto.getItemStatusList(),
                dto.getItemAssigneeList()
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

    public List<String> getItemAssignee() {
        return itemAssignee;
    }
}
