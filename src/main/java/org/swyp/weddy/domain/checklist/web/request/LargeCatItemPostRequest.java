package org.swyp.weddy.domain.checklist.web.request;

public class LargeCatItemPostRequest {
    private final String memberId;
    private final String title;

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
