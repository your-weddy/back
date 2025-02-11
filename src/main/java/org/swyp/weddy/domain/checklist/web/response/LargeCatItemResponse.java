package org.swyp.weddy.domain.checklist.web.response;

import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

public class LargeCatItemResponse {
    private Long id;
    private Long checklistId;
    private String title;

    public LargeCatItemResponse(Long id, Long checklistId, String title) {
        this.id = id;
        this.checklistId = checklistId;
        this.title = title;
    }

    public static LargeCatItemResponse from(LargeCatItem item) {
        return new LargeCatItemResponse(
                item.getId(),
                item.getChecklistId(),
                item.getTitle()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public String getTitle() {
        return title;
    }
}
