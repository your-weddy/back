package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    private final ChecklistMapper mapper;

    public ChecklistServiceImpl(ChecklistMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public int assignChecklist(ChecklistDto dto) {
        Checklist checklist = Checklist.from(dto);
        mapper.insertChecklist(checklist);

        return checklist.getId();
    }

    @Override
    public boolean hasChecklist(ChecklistDto dto) {
        throw new UnsupportedOperationException("not implemented yet!");
    }
}
