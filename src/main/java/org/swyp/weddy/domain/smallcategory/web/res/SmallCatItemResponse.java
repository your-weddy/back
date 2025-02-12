package org.swyp.weddy.domain.smallcategory.web.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SmallCatItemResponse {
    private Long id;
    private Long largeCatItemId;
    private String title;
    private Date dueDate;
    private String assigneeName;
    private String body;
    private String statusName;
    private Long amount;

    public static List<SmallCatItemResponse> from(List<SmallCatItem> smallCatItems) {
        return smallCatItems.stream()
                .map(item -> new SmallCatItemResponse(
                        item.getId(),
                        item.getLargeCatItemId(),
                        item.getTitle(),
                        item.getDueDate(),
                        item.getAssigneeName(),
                        item.getBody(),
                        item.getStatusName(),
                        item.getAmount()
                ))
                .collect(Collectors.toList());
    }

    public static SmallCatItemResponse from(SmallCatItem item) {
        return new SmallCatItemResponse(
                        item.getId(),
                        item.getLargeCatItemId(),
                        item.getTitle(),
                        item.getDueDate(),
                        item.getAssigneeName(),
                        item.getBody(),
                        item.getStatusName(),
                        item.getAmount()
                );
    }

    @Override
    public String toString() {
        return "SmallCatItemResponse{" +
                "id=" + id +
                ", largeCatItemId=" + largeCatItemId +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", assigneeName='" + assigneeName + '\'' +
                ", body='" + body + '\'' +
                ", statusName='" + statusName + '\'' +
                ", amount=" + amount +
                '}';
    }
}


