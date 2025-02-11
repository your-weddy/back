package org.swyp.weddy.domain.smallcategory.service.dto;

import lombok.AllArgsConstructor;
import java.util.Date;

@AllArgsConstructor
public class SmallCatItemDto {
    private Long id;
    private Long largeCatItemId;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String body;
    private String statusName;
    private Long amount;

}
