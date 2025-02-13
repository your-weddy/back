package org.swyp.weddy.domain.smallcategory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItem;
import org.swyp.weddy.domain.smallcategory.entity.SmallCatItemPreview;

import java.util.List;

@Mapper
public interface SmallCatItemMapper {

    List<SmallCatItemPreview> selectItemPreviews(@Param("checklistId")Long checklistId, @Param("largeCatItemId")Long largeCatItemId);
    SmallCatItem selectItem(@Param("checklistId")Long checklistId, @Param("largeCatItemId")Long largeCatItemId, @Param("smallCatItemId")Long smallCatItemId);

    Long insertItem(SmallCatItem smallCatItem);

    int updateItem(SmallCatItem smallCatItem);

    int deleteItem(@Param("largeCatItemId")Long largeCatItemId, @Param("smallCatItemId")Long smallCatItemId);
    int deleteAllItems(@Param("checklistId")Long checklistId, @Param("largeCatItemId")Long largeCatItemId);




}
