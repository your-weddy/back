package org.swyp.weddy.domain.checklist.service.dto;

public class ChecklistDto {
    private String memberId;

    public ChecklistDto(String memberId) {
        this.memberId = memberId;
    }

    public static ChecklistDto from(String memberId) {
        return new ChecklistDto(memberId);
    }

    public String getMemberId() {
        return memberId;
    }
}
