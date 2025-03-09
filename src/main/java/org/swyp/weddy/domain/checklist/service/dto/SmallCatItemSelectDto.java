package org.swyp.weddy.domain.checklist.service.dto;

import java.util.List;

public class SmallCatItemSelectDto {
    private Long checklistId;
    private Long largeCatItemId;
    private List<String> itemStatusList;
    private List<String> itemAssigneeList;

    public SmallCatItemSelectDto(Long checklistId, Long largeCatItemId, List<String> itemStatusList, List<String> itemAssigneeList) {
        this.checklistId = checklistId;
        this.largeCatItemId = largeCatItemId;
        this.itemStatusList = itemStatusList;
        this.itemAssigneeList = itemAssigneeList;
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

    public List<String> getItemStatusList() {
        return itemStatusList;
    }

    public List<String> getItemAssigneeList() {
        return itemAssigneeList;
    }
}
