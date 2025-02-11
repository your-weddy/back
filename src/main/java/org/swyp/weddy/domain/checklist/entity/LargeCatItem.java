package org.swyp.weddy.domain.checklist.entity;

import java.sql.Timestamp;

public class LargeCatItem {
    private Long id;
    private Long checklistId;
    private String title;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Boolean is_deleted;


    public LargeCatItem() {
    }

    public LargeCatItem(Long id, Long checklistId, String title, Timestamp created_at, Timestamp updated_at, Boolean is_deleted) {
        this.id = id;
        this.checklistId = checklistId;
        this.title = title;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
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
