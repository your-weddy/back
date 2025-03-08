package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.checklist.entity.SmallCatItem;
import org.swyp.weddy.domain.checklist.entity.SmallCatItemPreview;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemSelectDto;

import java.util.List;

@Mapper
public interface SmallCatMapper {

    List<SmallCatItemPreview> selectItemPreviews(@Param("checklistId")Long checklistId, @Param("largeCatItemId")Long largeCatItemId);
    List<SmallCatItemPreview> selectItemPreviewsBy(SmallCatItemSelectDto dto);
    SmallCatItem selectItem(@Param("checklistId")Long checklistId, @Param("largeCatItemId")Long largeCatItemId, @Param("smallCatItemId")Long smallCatItemId);
    Long insertItem(SmallCatItem smallCatItem);

    int updateItem(SmallCatItem smallCatItem);

    int deleteItem(@Param("largeCatItemId")Long largeCatItemId, @Param("smallCatItemId")Long smallCatItemId);
    int deleteAllItems(@Param("checklistId")Long checklistId, @Param("largeCatItemId")Long largeCatItemId);

    int moveItem(SmallCatItem item);

}
