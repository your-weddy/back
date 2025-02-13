package org.swyp.weddy.domain.checklist.web.request;

public class LargeCatItemEditRequest {
    private String memberId;
    private String id;
    private String editedTitle;

    public LargeCatItemEditRequest() {
    }

    public LargeCatItemEditRequest(String memberId, String id, String editedTitle) {
        this.memberId = memberId;
        this.id = id;
        this.editedTitle = editedTitle;
    }

    public String getMemberId() {
        return memberId;
    }
}
