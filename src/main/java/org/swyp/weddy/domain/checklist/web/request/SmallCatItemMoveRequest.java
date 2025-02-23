package org.swyp.weddy.domain.checklist.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmallCatItemMoveRequest {
    private Long checklistId;
    private Long largeCatItemId;
    private List<Long> smallCatItemIds;
}
