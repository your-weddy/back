package org.swyp.weddy.domain.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemMoveDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
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
    private Long sequence;
    private String attachedFileUrl;

    public SmallCatItem(Long id, Long largeCatItemId, String title, Date dueDate, String assigneeName, String body, String statusName, Long amount, String attachedFileUrl) {
        this.id = id;
        this.largeCatItemId = largeCatItemId;
        this.title = title;
        this.dueDate = dueDate;
        this.assigneeName = assigneeName;
        this.body = body;
        this.statusName = statusName;
        this.amount = amount;
        this.attachedFileUrl = attachedFileUrl;
    }

    public static SmallCatItem from(SmallCatItemDto dto) {
        return new SmallCatItem(
                dto.getId(),
                dto.getLargeCatItemId(),
                dto.getTitle(),
                dto.getDueDate(),
                dto.getAssigneeName(),
                dto.getBody(),
                dto.getStatusName(),
                dto.getAmount(),
                null,
                dto.getAttachedFileUrl()
        );
    }

    public static List<SmallCatItem> ofMove(SmallCatItemMoveDto dto) {

        List<Long> sequences = dto.getSmallCatItemIds();
        ArrayList<SmallCatItem> itemsAfterMove = new ArrayList<>(sequences.size());

        for (long i=0; i<sequences.size(); i++){
            SmallCatItem movedItem =
                    SmallCatItem.builder()
                    .id(sequences.get((int) i))
                    .largeCatItemId(dto.getLargeCatItemId())
                    .sequence((i))
                    .build();

            itemsAfterMove.add(movedItem);
        }

        return itemsAfterMove;
    }
}
