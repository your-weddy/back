package org.swyp.weddy.domain.checklist.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.swyp.weddy.domain.checklist.entity.SmallCatItem;

import java.util.Date;

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
    private String attachedFileUrl;

    public static SmallCatItemResponse from(SmallCatItem item) {
        return new SmallCatItemResponse(
                        item.getId(),
                        item.getLargeCatItemId(),
                        item.getTitle(),
                        item.getDueDate(),
                        item.getAssigneeName(),
                        item.getBody(),
                        item.getStatusName(),
                        item.getAmount(),
                        item.getAttachedFileUrl()
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


