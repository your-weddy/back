package org.swyp.weddy.domain.smallcategory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItemPreview;

import java.util.List;

@Mapper
public interface SmallCatItemMapper {

    List<SmallCatItemPreview> selectItemPreviews(@Param("checkListId")Long checkListId, @Param("largeCatItemId")Long largeCatItemId);
    SmallCatItem selectItem(@Param("checkListId")Long checkListId, @Param("largeCatItemId")Long largeCatItemId, @Param("smallCatItemId")Long smallCatItemId);
    Long insertItem(SmallCatItem smallCatItem);

    int updateItem(SmallCatItem smallCatItem);

    int deleteAllItems(@Param("largeCatItemId")Long largeCatItemId);
    int deleteItem(@Param("smallCatItemId")Long smallCatItemId);




}
