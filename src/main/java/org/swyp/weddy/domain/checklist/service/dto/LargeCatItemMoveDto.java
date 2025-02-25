package org.swyp.weddy.domain.checklist.service.dto;

import org.swyp.weddy.domain.checklist.web.request.LargeCatItemMoveRequest;

import java.util.List;

public class LargeCatItemMoveDto {
    private Long checklistId;
    private List<Long> idSequence;

    public LargeCatItemMoveDto(Long checklistId, List<Long> idSequence) {
        this.checklistId = checklistId;
        this.idSequence = idSequence;
    }

    public static LargeCatItemMoveDto of(Long checklistId, LargeCatItemMoveRequest request) {
        return new LargeCatItemMoveDto(
                checklistId,
                request.getIdSequence()
        );
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public List<Long> getIdSequence() {
        return idSequence;
    }

}
