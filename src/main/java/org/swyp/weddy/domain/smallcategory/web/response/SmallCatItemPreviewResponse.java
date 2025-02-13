package org.swyp.weddy.domain.smallcategory.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItemPreview;

import java.util.Date;
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


