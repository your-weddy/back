package org.swyp.weddy.domain.checklist.entity;

import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemMoveDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LargeCatItem {
    private Long id;
    private Long checklistId;
    private String title;

    private Long sequence;
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

    public LargeCatItem(Long checklistId, String title, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.checklistId = checklistId;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public LargeCatItem(Long id, Long checklistId, Long sequence, Timestamp updatedAt) {
        this.id = id;
        this.checklistId = checklistId;
        this.sequence = sequence;
        this.updatedAt = updatedAt;
    }

    public static LargeCatItem from(LargeCatItemAssignDto dto) {
        return new LargeCatItem(
                dto.getChecklistId(),
                dto.getTitle(),
                new Timestamp(System.currentTimeMillis()),
                null,
                Boolean.FALSE
        );
    }

    public static LargeCatItem ofEdit(LargeCatItem itemBeforeEdit, LargeCatItemEditDto dto) {
        return new LargeCatItem(
                dto.getId(),
                dto.getChecklistId(),
                dto.getNewTitle(),
                itemBeforeEdit.getCreatedAt(),
                new Timestamp(System.currentTimeMillis()),
                itemBeforeEdit.getIsDeleted()
        );
    }

    public static LargeCatItem ofDelete(LargeCatItem itemBeforeDelete, LargeCatItemDeleteDto dto) {
        return new LargeCatItem(
                dto.getId(),
                dto.getChecklistId(),
                itemBeforeDelete.getTitle(),
                itemBeforeDelete.getCreatedAt(),
                new Timestamp(System.currentTimeMillis()),
                Boolean.TRUE
        );
    }


    public static List<LargeCatItem> ofMove(LargeCatItemMoveDto dto) {
        List<Long> idSequence = dto.getIdSequence();
        ArrayList<LargeCatItem> itemsAfterMove = new ArrayList<>(idSequence.size());

        for (int i = 0; i < idSequence.size(); i++) {
            itemsAfterMove.add(new LargeCatItem(
                    idSequence.get(i),
                    dto.getChecklistId(),
                    (long) i,
                    new Timestamp(System.currentTimeMillis())
            ));
        }

        return itemsAfterMove;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }
}
