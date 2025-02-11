package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

@Mapper
public interface LargeCatMapper {
    LargeCatItem selectItem(@Param("checkListId") Long checkListId, @Param("id") Long id);
}
