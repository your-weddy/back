package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

public interface LargeCatService {
    LargeCatItemResponse findItem(Long memberId, Long id);
}
