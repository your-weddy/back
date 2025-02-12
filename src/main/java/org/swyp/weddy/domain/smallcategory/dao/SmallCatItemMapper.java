package org.swyp.weddy.domain.smallcategory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;

import java.util.List;

@Mapper
public interface SmallCatItemMapper {

    List<SmallCatItem> selectAllItems(@Param("checkListId")Long checkListId, @Param("largeCatItemId")Long largeCatItemId);
    Long insertItem(SmallCatItem smallCatItem);

    SmallCatItem selectItem(@Param("largeCatItemId")Long largeCatItemId, @Param("smallCatItemId")Long smallCatItemId);

    int updateItem(SmallCatItem smallCatItem);

    int deleteAllItems(@Param("largeCatItemId")Long largeCatItemId);
    int deleteItem(@Param("smallCatItemId")Long smallCatItemId);




}
