package org.swyp.weddy.domain.checklist.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.swyp.weddy.domain.checklist.entity.SmallCatItemPreview;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<SmallCatItemPreviewResponse> from(List<SmallCatItemPreview> SmallCatItemPreviews) {
        return SmallCatItemPreviews.stream()
                .map(item -> new SmallCatItemPreviewResponse(
                        item.getId(),
                        item.getLargeCatItemId(),
                        item.getTitle(),
                        item.getDueDate(),
                        item.getAssigneeName(),
                        item.getStatusName()
                ))
                .collect(Collectors.toList());
    }
}
