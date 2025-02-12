package org.swyp.weddy.domain.smallcategory.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.swyp.weddy.domain.smallcategory.web.request.SmallCatItemPatchRequest;
import org.swyp.weddy.domain.smallcategory.web.request.SmallCatItemPostRequest;

import java.util.Date;

@AllArgsConstructor
@Getter
public class SmallCatItemDto {
    private Long checkListId;
    private Long id;
    private Long largeCatItemId;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String body;
    private String statusName;
    private Long amount;

    public static SmallCatItemDto from(SmallCatItemPostRequest request) {
        return new SmallCatItemDto(
                request.getCheckListId(),
                null,
                request.getLargeCatItemId(),
                request.getTitle(),
                request.getDueDate(),
                request.getAssigneeName(),
                request.getBody(),
                request.getStatusName(),
                request.getAmount()
        );
    }
    public static SmallCatItemDto from(SmallCatItemPatchRequest request) {
        return new SmallCatItemDto(
                request.getCheckListId(),
                request.getId(),
                request.getLargeCatItemId(),
                request.getTitle(),
                request.getDueDate(),
                request.getAssigneeName(),
                request.getBody(),
                request.getStatusName(),
                request.getAmount()
        );
    }
}
