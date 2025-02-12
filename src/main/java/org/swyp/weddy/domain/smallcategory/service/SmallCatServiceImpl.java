package org.swyp.weddy.domain.smallcategory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.smallcategory.dao.SmallCatItemMapper;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;
import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.res.SmallCatItemResponse;

import java.util.List;

@Slf4j
@Service
public class SmallCatServiceImpl implements SmallCatService {

    private final SmallCatItemMapper mapper;

    public SmallCatServiceImpl(SmallCatItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SmallCatItemResponse> findItems(Long checkListId, Long largeCatItemId) {
        List<SmallCatItem> smallCatItems = mapper.selectAllItems(checkListId, largeCatItemId);
        List<SmallCatItemResponse> smallCatItemResponses = SmallCatItemResponse.from(smallCatItems);
        return smallCatItemResponses;
    }

    @Override
    public boolean deleteAll(Long checkListId, Long largeCatItemId) {
        int deletedRows = mapper.deleteAllItems(largeCatItemId);
        return deletedRows < 0;
    }

    @Transactional
    @Override
    public Long assignItem(SmallCatItemDto dto) {
        SmallCatItem smallCatItem = SmallCatItem.from(dto);
        Long smallCatItemId = mapper.insertItem(smallCatItem);
        return smallCatItemId;
    }

    @Override
    public SmallCatItemResponse findDetails(SmallCatItemDto dto) {
        Long largeCatItemId = dto.getLargeCatItemId();
        Long smallCatItemId = dto.getId();
        SmallCatItem smallCatItem = mapper.selectItem(largeCatItemId, smallCatItemId);
        return SmallCatItemResponse.from(smallCatItem);
    }

    @Transactional
    @Override
    public boolean edit(SmallCatItemDto dto) {
        SmallCatItem smallCatItem = SmallCatItem.from(dto);
        int updatedRows = mapper.updateItem(smallCatItem);
        return updatedRows > 0;
    }

    @Override
    public boolean delete(SmallCatItemDto dto) {
        Long smallCatItemId = dto.getId();
        int deletedRows = mapper.deleteItem(smallCatItemId);
        return deletedRows > 0;
    }
}
