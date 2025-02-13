package org.swyp.weddy.domain.checklist.web.request;

public class LargeCatItemDeleteRequest {
    private String memberId;
    private String id;

    public LargeCatItemDeleteRequest(String memberId, String id) {
        this.memberId = memberId;
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getId() {
        return id;
    }
}
