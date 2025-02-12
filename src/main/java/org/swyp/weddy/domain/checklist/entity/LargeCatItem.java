package org.swyp.weddy.domain.checklist.entity;

import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;

import java.sql.Timestamp;

public class LargeCatItem {
    private Long id;
    private Long checklistId;
    private String title;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDeleted;


    public LargeCatItem() {
    }

    public LargeCatItem(Long id, Long checklistId, String title, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.id = id;
        this.checklistId = checklistId;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public static LargeCatItem from(LargeCatItemAssignDto dto) {
        return null;
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
