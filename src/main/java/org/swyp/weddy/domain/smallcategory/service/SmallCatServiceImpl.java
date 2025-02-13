package org.swyp.weddy.domain.smallcategory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.smallcategory.dao.SmallCatItemMapper;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItemPreview;
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
        List<SmallCatItemPreviewResponse> smallCatItemPreviewResponsesResponses = SmallCatItemPreviewResponse.from(smallCatItems);
        return smallCatItemPreviewResponsesResponses;
    }
    @Override
    public SmallCatItemResponse findItem(Long checkListId, Long largeCatItemId, Long smallCatItemId) {
        SmallCatItem smallCatItems = mapper.selectItem(checkListId, largeCatItemId, smallCatItemId);
        SmallCatItemResponse smallCatItemResponse = SmallCatItemResponse.from(smallCatItems);
        return smallCatItemResponse;
    }

    @Override
    public boolean deleteAll(Long checkListId, Long largeCatItemId) {
        int deletedRows = mapper.deleteAllItems(checkListId, largeCatItemId);
        return deletedRows < 0;
    }

    @Transactional
    @Override
    public Long addItem(SmallCatItemDto dto) {
        SmallCatItem smallCatItem = SmallCatItem.from(dto);
        Long smallCatItemId = mapper.insertItem(smallCatItem);
        return smallCatItemId;
    }

    @Transactional
    @Override
    public boolean editItem(SmallCatItemDto dto) {
        SmallCatItem smallCatItem = SmallCatItem.from(dto);
        int updatedRows = mapper.updateItem(smallCatItem);
        return updatedRows > 0;
    }

    @Override
    public boolean deleteItem(Long largeCatItemId, Long smallCatItemId) {
        int deletedRows = mapper.deleteItem(largeCatItemId, smallCatItemId);
        return deletedRows > 0;
    }
}
