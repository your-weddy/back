package org.swyp.weddy.domain.checklist.web.request;

public class LargeCatItemPostRequest {
    private String memberId;
    private String title;

    public LargeCatItemPostRequest() {
    }

    public LargeCatItemPostRequest(String memberId, String title) {
        this.memberId = memberId;
        this.title = title;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }
}
