package org.swyp.weddy.domain.checklist.web.response;

import org.swyp.weddy.domain.checklist.entity.Checklist;

public class ChecklistResponse {
    private final Long id;
    private final String memberId;
    private final Integer dDay;

    public ChecklistResponse(Long id, String memberId, Integer dDay) {
        this.id = id;
        this.memberId = memberId;
        this.dDay = dDay;
    }

    public static ChecklistResponse from(Checklist checklist) {
        return new ChecklistResponse(
                checklist.getId(),
                String.valueOf(checklist.getMemberId()),
                0 //checklist.getdDay()
        );
    }

    public Long getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public Integer getdDay() {
        return dDay;
    }
}
