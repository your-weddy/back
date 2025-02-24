package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

import java.util.List;

@Mapper
public interface LargeCatMapper {
    LargeCatItem selectItem(@Param("checklistId") Long checklistId, @Param("id") Long id);

    List<LargeCatItem> selectAllItems(Long checklistId);

    Long insertItem(LargeCatItem item);

    Long updateItem(LargeCatItem item);

    Long deleteItem(LargeCatItem item);

    int updateItemSequence(LargeCatItem item);
}
