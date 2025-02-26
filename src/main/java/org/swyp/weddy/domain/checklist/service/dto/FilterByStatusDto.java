package org.swyp.weddy.domain.checklist.service.dto;

import java.util.Arrays;
import java.util.List;

public class FilterByStatusDto {
    private Long checklistId;
    private List<String> itemStatusList;

    public FilterByStatusDto(Long checklistId, List<String> itemStatusList) {
        this.checklistId = checklistId;
        this.itemStatusList = itemStatusList;
    }

    public static FilterByStatusDto from(Long checklistId, String itemStatuses) {
        return new FilterByStatusDto(
                checklistId,
                FilterByStatusDto.convertToList(itemStatuses)
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
