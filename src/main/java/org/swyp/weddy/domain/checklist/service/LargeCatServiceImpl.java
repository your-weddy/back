package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

@Service
public class LargeCatServiceImpl implements LargeCatService {

    private final LargeCatMapper mapper;

    public LargeCatServiceImpl(LargeCatMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LargeCatItemResponse findItem(Long checklistId, Long id) {
        LargeCatItem largeCatItem = mapper.selectItem(checklistId, id);

        if (largeCatItem == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        return LargeCatItemResponse.from(largeCatItem);
    }
}
