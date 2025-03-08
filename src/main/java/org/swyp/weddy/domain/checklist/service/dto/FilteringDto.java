package org.swyp.weddy.domain.checklist.service.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilteringDto {
    private Long checklistId;
    private List<String> itemStatusList;
    private List<String> itemAssigneeList;

    public FilteringDto(Long checklistId, List<String> itemStatusList, List<String> itemAssigneeList) {
        this.checklistId = checklistId;
        this.itemStatusList = itemStatusList;
        this.itemAssigneeList = itemAssigneeList;
    }

    public static FilteringDto of(Long checklistId, String itemStatuses, String itemAssignees) {
        return new FilteringDto(
                checklistId,
                FilteringDto.convertToList(itemStatuses),
                FilteringDto.convertToList(itemAssignees)
        );
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public List<String> getItemStatusList() {
        return itemStatusList;
    }

    public List<String> getItemAssigneeList() {
        return itemAssigneeList;
    }

    static List<String> convertToList(String filteringCondition) {
        if (filteringCondition.equals("")) {
            return Collections.emptyList();
        }

        return Arrays.stream(filteringCondition.split(",")).map(String::strip).toList();
    }
}
