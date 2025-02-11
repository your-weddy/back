package org.swyp.weddy.domain.smallcategory.service.dto;

import java.util.Date;

public class SmallCatItemDto {
    private Long checkListId;
    private Long largeCatItemId;
    private Long id;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String body;
    private String statusName;
    private Long amount;

    public SmallCatItemDto(Long checkListId, Long largeCatItemId, Long id, String title, Date dueDate, String assigneeName, String body, String statusName, Long amount) {
        this.checkListId = checkListId;
        this.largeCatItemId = largeCatItemId;
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.assigneeName = assigneeName;
        this.body = body;
        this.statusName = statusName;
        this.amount = amount;
    }
}
