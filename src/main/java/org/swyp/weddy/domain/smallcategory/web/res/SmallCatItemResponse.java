package org.swyp.weddy.domain.smallcategory.web.res;

import lombok.AllArgsConstructor;
import java.util.Date;

@AllArgsConstructor
public class SmallCatItemResponse {
    private Long checkListId;
    private Long largeCatItemId;
    private Long id;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String body;
    private String statusName;
    private Long amount;
}