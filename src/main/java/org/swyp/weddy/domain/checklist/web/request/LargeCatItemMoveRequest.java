package org.swyp.weddy.domain.checklist.web.request;

import java.util.List;

public class LargeCatItemMoveRequest {
    private String memberId;
    private List<Long> idSequence;

    public LargeCatItemMoveRequest() {
    }

    public LargeCatItemMoveRequest(String memberId, List<Long> idSequnce) {
        this.memberId = memberId;
        this.idSequence = idSequnce;
    }

    public String getMemberId() {
        return memberId;
    }

    public List<Long> getIdSequence() {
        return idSequence;
    }

}