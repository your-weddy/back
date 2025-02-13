package org.swyp.weddy.domain.smallcategory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.smallcategory.dao.SmallCatItemMapper;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItemPreview;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemAddException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemDeleteException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemNotExistsException;
import org.swyp.weddy.domain.smallcategory.exception.SmallCategoryItemUpdateException;
import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemResponse;

import java.util.List;

@Slf4j
@Service
public class SmallCatServiceImpl implements SmallCatService {

    private final SmallCatItemMapper mapper;

    public SmallCatServiceImpl(SmallCatItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SmallCatItemPreviewResponse> findItemPreviews(Long checklistId, Long largeCatItemId) {

        List<SmallCatItemPreview> smallCatItems = mapper.selectItemPreviews(checklistId, largeCatItemId);
        if (smallCatItems.isEmpty()) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        List<SmallCatItemPreviewResponse> smallCatItemPreviewResponsesResponses = SmallCatItemPreviewResponse.from(smallCatItems);
        return smallCatItemPreviewResponsesResponses;
    }

    @Override
    public SmallCatItemResponse findItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {

        SmallCatItem smallCatItems = mapper.selectItem(checklistId, largeCatItemId, smallCatItemId);
        if (smallCatItems == null) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        SmallCatItemResponse smallCatItemResponse = SmallCatItemResponse.from(smallCatItems);
        return smallCatItemResponse;
    }

    @Transactional
    @Override
    public Long addItem(SmallCatItemDto dto) {

        SmallCatItem smallCatItem = SmallCatItem.from(dto);
        Long smallCatItemId = insertItemOrThrow(smallCatItem);
        if (smallCatItemId == 0) {
            throw new SmallCategoryItemAddException(ErrorCode.ADD_FAILED);
        }

        return smallCatItemId;
    }

    private Long insertItemOrThrow(SmallCatItem smallCatItem) { // 예외 발생을 명확히 표현
        try {
            return mapper.insertItem(smallCatItem);
        } catch (Exception e) {
            throw new SmallCategoryItemAddException(ErrorCode.ADD_FAILED);
        }
    }

    @Transactional
    @Override
    public boolean editItem(SmallCatItemDto dto) {

        SmallCatItem smallCatItems = mapper.selectItem(dto.getChecklistId(), dto.getLargeCatItemId(), dto.getId());
        if (smallCatItems == null) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        SmallCatItem smallCatItem = SmallCatItem.from(dto);
        int updatedRows = updateItemOrThrow(smallCatItem);
        if (updatedRows == 0) {
            throw new SmallCategoryItemUpdateException(ErrorCode.UPDATE_FAILED);
        }

        return updatedRows > 0;

    }

    private int updateItemOrThrow(SmallCatItem smallCatItem) { // 예외 발생을 명확히 표현
        try {
            return mapper.updateItem(smallCatItem);
        } catch (Exception e) {
            throw new SmallCategoryItemUpdateException(ErrorCode.UPDATE_FAILED);
        }
    }

    @Transactional
    @Override
    public boolean deleteItem(Long checklistId, Long largeCatItemId, Long smallCatItemId) {

        SmallCatItem smallCatItems = mapper.selectItem(checklistId, largeCatItemId, smallCatItemId);
        if (smallCatItems == null) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        int deletedRows = deleteItemOrThrow(largeCatItemId, smallCatItemId);
        if (deletedRows == 0) {
            throw new SmallCategoryItemDeleteException(ErrorCode.DELETE_FAILED);
        }

        return deletedRows > 0;

    }

    private int deleteItemOrThrow(Long largeCatItemId, Long smallCatItemId) { // 예외 발생을 명확히 표현
        try {
            return mapper.deleteItem(largeCatItemId, smallCatItemId);
        } catch (Exception e) {
            throw new SmallCategoryItemDeleteException(ErrorCode.DELETE_FAILED);
        }
    }

    @Transactional
    @Override
    public boolean deleteAll(Long checklistId, Long largeCatItemId) {

        List<SmallCatItemPreview> smallCatItems = mapper.selectItemPreviews(checklistId, largeCatItemId);
        if (smallCatItems.isEmpty()) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        int deletedRows = mapper.deleteAllItems(checklistId, largeCatItemId);
        if (deletedRows == 0) {
            throw new SmallCategoryItemDeleteException(ErrorCode.DELETE_FAILED);
        }

        return deletedRows > 0;

    }

    private int deleteAllItemsOrThrow(Long checklistId, Long largeCatItemId) { // 예외 발생을 명확히 표현
        try {
            return mapper.deleteAllItems(checklistId, largeCatItemId);
        } catch (Exception e) {
            throw new SmallCategoryItemDeleteException(ErrorCode.DELETE_FAILED);
        }
    }

}
