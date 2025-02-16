package org.swyp.weddy.domain.checklist.web.response;

import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

import java.util.Collections;
import java.util.List;

public class LargeCatItemResponse {
    private Long id;
    private Long checklistId;
    private String title;
    private List<SmallCatItemPreviewResponse> smallCatItems;

    public LargeCatItemResponse(Long id, Long checklistId, String title) {
        this.id = id;
        this.checklistId = checklistId;
        this.title = title;
        this.smallCatItems = Collections.emptyList();
    }

    public static LargeCatItemResponse from(LargeCatItem item) {
        return new LargeCatItemResponse(
                item.getId(),
                item.getChecklistId(),
                item.getTitle()
        );
    }

    public LargeCatItemResponse withSmallCatItems(List<SmallCatItemPreviewResponse> smallCatItems) {
        this.smallCatItems = smallCatItems != null ? smallCatItems : Collections.emptyList();
        return this;
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

    public List<SmallCatItemPreviewResponse> getSmallCatItems() {
        return smallCatItems;
    }
}
