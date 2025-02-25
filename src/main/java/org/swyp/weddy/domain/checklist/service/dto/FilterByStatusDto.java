package org.swyp.weddy.domain.checklist.service.dto;

import java.util.Arrays;
import java.util.List;

public class FilterByStatusDto {
    private Long checklistId;
    private List<String> itemStatus;

    public FilterByStatusDto(Long checklistId, List<String> itemStatus) {
        this.checklistId = checklistId;
        this.itemStatus = itemStatus;
    }

    public static FilterByStatusDto from(Long checklistId, String itemStatus) {
        return new FilterByStatusDto(
                checklistId,
                FilterByStatusDto.convertToList(itemStatus)
        );
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public List<String> getItemStatus() {
        return itemStatus;
    }

    static List<String> convertToList(String statuses) {
        return Arrays.stream(statuses.split(",")).map(String::strip).toList();
    }
}
