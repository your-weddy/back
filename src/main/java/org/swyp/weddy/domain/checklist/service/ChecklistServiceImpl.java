package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.exception.ChecklistAlreadyAssignedException;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    private final ChecklistMapper mapper;

    public ChecklistServiceImpl(ChecklistMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public int assignChecklist(ChecklistDto dto) {
        if (hasChecklist(dto)) {
            throw new ChecklistAlreadyAssignedException(ErrorCode.DUPLICATE_CHECKLIST);
        }

        Checklist checklist = Checklist.from(dto);
        mapper.insertChecklist(checklist);

        return checklist.getId();
    }

    @Override
    public boolean hasChecklist(ChecklistDto dto) {
        Long memberId = Long.valueOf(dto.getMemberId());
        Checklist checklist = mapper.selectChecklistByMemberId(memberId);

        return checklist != null;
    }

    @Override
    public ChecklistResponse findChecklist(ChecklistDto dto) {
        return null;
    }
}
