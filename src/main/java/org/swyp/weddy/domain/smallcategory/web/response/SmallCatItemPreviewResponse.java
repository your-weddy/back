package org.swyp.weddy.domain.smallcategory.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SmallCatItemPreviewResponse {
    private Long id;
    private Long largeCatItemId;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String statusName;
}