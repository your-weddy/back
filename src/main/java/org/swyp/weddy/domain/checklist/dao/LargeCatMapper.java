package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

@Mapper
public interface LargeCatMapper {
    LargeCatItem selectItem(@Param("checklistId") Long checklistId, @Param("id") Long id);

    Long insertItem(LargeCatItem item);

    Long updateItem(LargeCatItem item);
}
