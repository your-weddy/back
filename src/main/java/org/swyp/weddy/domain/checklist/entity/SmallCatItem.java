package org.swyp.weddy.domain.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class SmallCatItem {

    private Long id;
    private Long largeCatItemId;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String body;
    private String statusName;
    private Long amount;

    public static SmallCatItem from(SmallCatItemDto dto) {
        return new SmallCatItem(
                dto.getId(),
                dto.getLargeCatItemId(),
                dto.getTitle(),
                dto.getDueDate(),
                dto.getAssigneeName(),
                dto.getBody(),
                dto.getStatusName(),
                dto.getAmount()
        );
    }
}
