package org.swyp.weddy.domain.checklist.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChecklistMapper {
    String insertChecklist(String memberId);
}
