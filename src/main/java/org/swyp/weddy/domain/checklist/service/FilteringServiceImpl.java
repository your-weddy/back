package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

public class FilteringServiceImpl implements FilteringService {

    private final LargeCatMapper mapper;
    private final SmallCatService smallCatService;

    public FilteringServiceImpl(LargeCatMapper mapper, SmallCatService smallCatService) {
        this.mapper = mapper;
        this.smallCatService = smallCatService;
    }

    @Override
    public List<LargeCatItemResponse> filterByStatus(Long checklistId, String itemStatus) {
        return null;
    }
}
