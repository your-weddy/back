package org.swyp.weddy.domain.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class SmallCatItemPreview {

    private Long id;
    private Long largeCatItemId;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String statusName;
    private Long sequence;

    public SmallCatItemPreview(Long id, Long largeCatItemId, String title, Date dueDate, String assigneeName, String statusName) {
        this.id = id;
        this.largeCatItemId = largeCatItemId;
        this.title = title;
        this.dueDate = dueDate;
        this.assigneeName = assigneeName;
        this.statusName = statusName;
    }
}
