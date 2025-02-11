package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

public class LargeCatServiceImpl implements LargeCatService {
    @Override
    public LargeCatItemResponse findItem(Long memberId, Long id) {
        return new LargeCatItemResponse();
    }
}
