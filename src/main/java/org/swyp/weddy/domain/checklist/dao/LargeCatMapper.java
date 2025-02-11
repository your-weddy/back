package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.checklist.entity.LargeCatItem;

@Mapper
public interface LargeCatMapper {
    LargeCatItem selectItem(Long checkListId, Long id);
}
