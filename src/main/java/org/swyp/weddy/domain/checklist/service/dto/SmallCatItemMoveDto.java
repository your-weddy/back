package org.swyp.weddy.domain.checklist.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemMoveRequest;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class SmallCatItemMoveDto {
    private Long checklistId;
    private Long largeCatItemId;
    private List<Long> smallCatItemIds;


    public static SmallCatItemMoveDto from(SmallCatItemMoveRequest request) {
        return new SmallCatItemMoveDto(
                request.getChecklistId(),
                request.getLargeCatItemId(),
                request.getSmallCatItemIds()
        );
    }
}