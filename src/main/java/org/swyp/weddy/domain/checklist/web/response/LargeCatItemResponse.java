package org.swyp.weddy.domain.checklist.web.response;

import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

public class LargeCatItemResponse {
    public static LargeCatItemResponse from(LargeCatItem item) {
        return new LargeCatItemResponse();
    }
}
