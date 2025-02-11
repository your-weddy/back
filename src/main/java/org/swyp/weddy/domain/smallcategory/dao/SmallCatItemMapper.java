package org.swyp.weddy.domain.smallcategory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;

import java.util.List;

@Mapper
public interface SmallCatItemMapper {

    List<SmallCatItem> selectAllItems(Long checkListId, Long largeCatItemId);
    int insertItem(SmallCatItem smallCatItem);

    SmallCatItem selectItem(Long checkListId, Long largeCatItemId, Long smallCatItemId);

    void updateItem(SmallCatItem smallCatItem);

    void deleteItem(Long smallCatItem);


}
