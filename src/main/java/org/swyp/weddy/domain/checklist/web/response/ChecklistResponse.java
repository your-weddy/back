package org.swyp.weddy.domain.checklist.web.response;

import org.swyp.weddy.domain.checklist.entity.Checklist;

import java.util.Date;

public class ChecklistResponse {
    private final Integer id;
    private final String memberId;
    private final Date dDay;

    public ChecklistResponse(Integer id, String memberId, Date dDay) {
        this.id = id;
        this.memberId = memberId;
        this.dDay = dDay;
    }

    public static ChecklistResponse from(Checklist checklist) {
        return new ChecklistResponse(
                checklist.getId(),
                checklist.getMemberId(),
                checklist.getdDay()
        );
    }
}
