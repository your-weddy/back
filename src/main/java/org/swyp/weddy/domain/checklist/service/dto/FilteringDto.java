package org.swyp.weddy.domain.checklist.service.dto;

import java.util.Arrays;
import java.util.List;

public class FilteringDto {
    private Long checklistId;
    private List<String> itemStatusList;

    public FilteringDto(Long checklistId, List<String> itemStatusList) {
        this.checklistId = checklistId;
        this.itemStatusList = itemStatusList;
    }

    public static FilteringDto from(Long checklistId, String itemStatuses) {
        return new FilteringDto(
                checklistId,
                FilteringDto.convertToList(itemStatuses)
        );
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public List<String> getItemStatusList() {
        return itemStatusList;
    }

    static List<String> convertToList(String itemStatuses) {
        return Arrays.stream(itemStatuses.split(",")).map(String::strip).toList();
    }
}
