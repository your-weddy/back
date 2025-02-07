package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;

@Mapper
public interface ChecklistMapper {
    int insertChecklist(Checklist checklist);
}
