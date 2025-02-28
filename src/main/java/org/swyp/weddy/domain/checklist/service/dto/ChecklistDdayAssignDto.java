package org.swyp.weddy.domain.checklist.service.dto;

import org.swyp.weddy.domain.checklist.web.request.ChecklistDdayAssignRequest;

import java.time.LocalDate;

public class ChecklistDdayAssignDto {
    private String memberId;
    private LocalDate dDay;

    public ChecklistDdayAssignDto(String memberId, LocalDate dDay) {
        this.memberId = memberId;
        this.dDay = dDay;
    }

    public static ChecklistDdayAssignDto from(String memberId, ChecklistDdayAssignRequest request) {
        return new ChecklistDdayAssignDto(memberId, request.getdDay());
    }

    public String getMemberId() {
        return memberId;
    }
}
