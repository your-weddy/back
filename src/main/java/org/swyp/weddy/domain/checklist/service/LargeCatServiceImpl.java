package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

public class LargeCatServiceImpl implements LargeCatService {

    private final LargeCatMapper mapper;

    public LargeCatServiceImpl(LargeCatMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LargeCatItemResponse findItem(Long checklistId, Long id) {
        LargeCatItem largeCatItem = mapper.selectItem(checklistId, id);
        return LargeCatItemResponse.from(largeCatItem);
    }
}
