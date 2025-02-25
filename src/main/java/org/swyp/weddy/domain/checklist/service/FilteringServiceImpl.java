package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.FilterByStatusDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemSelectDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilteringServiceImpl implements FilteringService {

    private final LargeCatMapper mapper;
    private final SmallCatService smallCatService;

    public FilteringServiceImpl(LargeCatMapper mapper, SmallCatService smallCatService) {
        this.mapper = mapper;
        this.smallCatService = smallCatService;
    }

    @Override
    public List<LargeCatItemResponse> filterByStatus(FilterByStatusDto dto) {
        List<LargeCatItem> allItems = mapper.selectAllItems(dto.getChecklistId());

        if (allItems.isEmpty()) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        List<LargeCatItemResponse> result = new ArrayList<>();
        for (LargeCatItem item : allItems) {
            SmallCatItemSelectDto smallDto = SmallCatItemSelectDto.from(dto, item.getId());
            List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviewsByStatus(
                    smallDto
            );

            if (itemPreviews != null) {
                LargeCatItemResponse itemWithSmallItems = LargeCatItemResponse.from(item).withSmallCatItems(itemPreviews);
                result.add(itemWithSmallItems);
            }
        }

        return result;
    }
}
