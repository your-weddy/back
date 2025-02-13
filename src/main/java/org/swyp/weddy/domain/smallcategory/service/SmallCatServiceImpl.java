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
    public List<SmallCatItemPreviewResponse> findItemPreviews(Long checkListId, Long largeCatItemId) {

        List<SmallCatItemPreview> smallCatItems = mapper.selectItemPreviews(checkListId, largeCatItemId);
        if (smallCatItems.isEmpty()) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        List<SmallCatItemPreviewResponse> smallCatItemPreviewResponsesResponses = SmallCatItemPreviewResponse.from(smallCatItems);
        return smallCatItemPreviewResponsesResponses;
    }
    @Override
    public SmallCatItemResponse findItem(Long checkListId, Long largeCatItemId, Long smallCatItemId) {

        SmallCatItem smallCatItems = mapper.selectItem(checkListId, largeCatItemId, smallCatItemId);
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

        try {
            Long smallCatItemId = mapper.insertItem(smallCatItem);
            return smallCatItemId;
        } catch (Exception e) {
            throw new SmallCategoryItemAddException(ErrorCode.ADD_FAILED);
        }
    }

    @Transactional
    @Override
    public boolean editItem(SmallCatItemDto dto) {

        SmallCatItem smallCatItems = mapper.selectItem(dto.getCheckListId(), dto.getLargeCatItemId(), dto.getId());
        if (smallCatItems == null) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        try {
            SmallCatItem smallCatItem = SmallCatItem.from(dto);
            int updatedRows = mapper.updateItem(smallCatItem);
            return updatedRows > 0;
        } catch (Exception e) {
            throw new SmallCategoryItemUpdateException(ErrorCode.UPDATE_FAILED);

        }
    }

    @Override
    public boolean deleteItem(Long checkListId, Long largeCatItemId, Long smallCatItemId) {

        SmallCatItem smallCatItems = mapper.selectItem(checkListId, largeCatItemId, smallCatItemId);
        if (smallCatItems == null) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        try {
            int deletedRows = mapper.deleteItem(largeCatItemId, smallCatItemId);
            return deletedRows > 0;
        }
        catch (Exception e){
            throw new SmallCategoryItemDeleteException(ErrorCode.DELETE_FAILED);
        }
    }

    @Override
    public boolean deleteAll(Long checkListId, Long largeCatItemId) {

        List<SmallCatItemPreview> smallCatItems = mapper.selectItemPreviews(checkListId, largeCatItemId);
        if (smallCatItems.isEmpty()) {
            throw new SmallCategoryItemNotExistsException(ErrorCode.NOT_EXISTS);
        }

        try {
            int deletedRows = mapper.deleteAllItems(checkListId, largeCatItemId);
            return deletedRows > 0;
        }
        catch (Exception e){
            throw new SmallCategoryItemDeleteException(ErrorCode.DELETE_FAILED);
        }
    }

}
