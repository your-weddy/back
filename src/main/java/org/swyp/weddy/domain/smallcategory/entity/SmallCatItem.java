package org.swyp.weddy.domain.smallcategory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;

import java.util.Date;

@Getter
@AllArgsConstructor
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
