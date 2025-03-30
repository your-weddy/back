package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.LargeCatMapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemNotExistsException;
import org.swyp.weddy.domain.checklist.exception.LargeCatItemUpdateException;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemMoveDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;

import java.util.ArrayList;
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

        if (largeCatItem == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviews(checklistId, id);

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

    @Transactional
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

    @Transactional
    @Override
    public Long deleteItemWithSmallItems(LargeCatItemDeleteDto dto) {
        LargeCatItem itemBeforeDelete = mapper.selectItem(dto.getChecklistId(), dto.getId());

        if (itemBeforeDelete == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        smallCatService.deleteAll(dto.getChecklistId(), dto.getId());

        LargeCatItem largeCatItem = LargeCatItem.ofDelete(itemBeforeDelete, dto);
        mapper.deleteItem(largeCatItem);

        return largeCatItem.getId();
    }

    @Override
    public List<LargeCatItemResponse> findAllItems(Long checklistId) {
        List<LargeCatItem> allItems = mapper.selectAllItems(checklistId);

        if (allItems == null) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviewsByChecklistId(checklistId);

        List<LargeCatItemResponse> result = new ArrayList<>();
        for (LargeCatItem item : allItems) {
            LargeCatItemResponse itemWithSmallItems = LargeCatItemResponse.from(item).withAllSmallCatItems(itemPreviews);
            result.add(itemWithSmallItems);
        }


        return result;
    }

    @Override
    public void moveItem(LargeCatItemMoveDto dto) {

        if (dto.getIdSequence() == null || dto.getIdSequence().isEmpty()) {
            throw new LargeCatItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        List<LargeCatItem> itemsAfterMove = LargeCatItem.ofMove(dto);
        for (LargeCatItem item : itemsAfterMove) {
            int updatedRow = mapper.updateItemSequence(item);
            if (updatedRow != 1) {
                throw new LargeCatItemUpdateException(ErrorCode.UPDATE_FAILED);
            }
        }
    }
}
