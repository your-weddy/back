package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.smallcategory.service.SmallCatService;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;

import java.util.List;

@Service
public class LargeCatServiceImpl implements LargeCatService {

    private final LargeCatMapper mapper;
    private final SmallCatService smallCatService;

    public LargeCatServiceImpl(LargeCatMapper mapper, SmallCatService smallCatService) {
        this.mapper = mapper;
        this.smallCatService = smallCatService;
    }

    @Override
    public LargeCatItemResponse findItem(Long checklistId, Long id) {
        LargeCatItem largeCatItem = mapper.selectItem(checklistId, id);

        if (largeCatItem == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        return LargeCatItemResponse.from(largeCatItem);
    }

    @Override
    public LargeCatItemResponse findItemWithSmallItems(Long checklistId, Long id) {
        LargeCatItem largeCatItem = mapper.selectItem(checklistId, id);
        List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviews(checklistId, id);

        if (largeCatItem == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        return LargeCatItemResponse.from(largeCatItem)
                .withSmallCatItems(itemPreviews);
    }

    @Transactional
    @Override
    public Long addItem(LargeCatItemAssignDto dto) {
        LargeCatItem largeCatItem = LargeCatItem.from(dto);
        mapper.insertItem(largeCatItem);

        return largeCatItem.getId();
    }

    @Transactional
    @Override
    public Long editItem(LargeCatItemEditDto dto) {
        LargeCatItem itemBeforeEdit = mapper.selectItem(dto.getChecklistId(), dto.getId());

        if (itemBeforeEdit == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        LargeCatItem largeCatItem = LargeCatItem.ofEdit(itemBeforeEdit, dto);
        mapper.updateItem(largeCatItem);

        return largeCatItem.getId();
    }

    @Override
    public Long deleteItem(LargeCatItemDeleteDto dto) {
        LargeCatItem itemBeforeDelete = mapper.selectItem(dto.getChecklistId(), dto.getId());

        if (itemBeforeDelete == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        LargeCatItem largeCatItem = LargeCatItem.ofDelete(itemBeforeDelete, dto);
        mapper.deleteItem(largeCatItem);

        return largeCatItem.getId();
    }

    @Override
    public List<LargeCatItemResponse> findAllItems(Long checklistId) {
        return null;
    }
}
