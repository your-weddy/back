package org.swyp.weddy.domain.checklist.service.dto;

import org.swyp.weddy.domain.checklist.web.request.ChecklistCreateRequest;

public class ChecklistDto {
    private String memberId;

    public ChecklistDto(String memberId) {
        this.memberId = memberId;
    }

    public static ChecklistDto from(String memberId) {
        return new ChecklistDto(memberId);
    }

    public static ChecklistDto fromCreateRequest(ChecklistCreateRequest request) {
        return new ChecklistDto(request.getMemberId());
    }

    public String getMemberId() {
        return memberId;
    }
}
