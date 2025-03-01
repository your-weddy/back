package org.swyp.weddy.domain.checklist.web.request;

public class ChecklistCreateRequest {
    private String memberId;

    public ChecklistCreateRequest() {
    }

    public ChecklistCreateRequest(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
