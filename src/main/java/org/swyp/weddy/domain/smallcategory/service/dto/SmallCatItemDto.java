package org.swyp.weddy.domain.smallcategory.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
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

//    public static from();
}
