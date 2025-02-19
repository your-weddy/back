package org.swyp.weddy.domain.checklist.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPatchRequest;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPostRequest;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class SmallCatItemDto {
    private Long checklistId;
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
                request.getChecklistId(),
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
                request.getChecklistId(),
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
