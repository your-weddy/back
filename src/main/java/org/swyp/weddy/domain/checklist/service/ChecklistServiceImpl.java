package org.swyp.weddy.domain.checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.checklist.dao.ChecklistMapper;
import org.swyp.weddy.domain.checklist.entity.Checklist;
import org.swyp.weddy.domain.checklist.exception.ChecklistAlreadyAssignedException;
import org.swyp.weddy.domain.checklist.exception.ChecklistNotExistsException;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDdayAssignDto;
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
    public Long assignChecklist(ChecklistDto dto) {
        if (hasChecklist(dto)) {
            throw new ChecklistAlreadyAssignedException(ErrorCode.DUPLICATE_CHECKLIST);
        }

        Checklist checklist = Checklist.from(dto);
        mapper.insertChecklist(checklist);

        mapper.insertTemplateData(checklist);

        return checklist.getId();
    }

    @Override
    public boolean hasChecklist(ChecklistDto dto) {
        Long memberId = Long.valueOf(dto.getMemberId());
        Checklist checklist = mapper.selectChecklistByMemberId(memberId);

        if (checklist != null) {
            throw new ChecklistAlreadyAssignedException(ErrorCode.DUPLICATE_CHECKLIST);
        }
        return false;
    }

    @Override
    public ChecklistResponse findChecklist(ChecklistDto dto) {
        Long memberId = Long.valueOf(dto.getMemberId());
        Checklist checklist = mapper.selectChecklistByMemberId(memberId);
        if (checklist == null) {
            throw new ChecklistNotExistsException(ErrorCode.NOT_EXISTS);
        }
        return ChecklistResponse.from(checklist);
    }

    @Transactional
    @Override
    public Long editDday(ChecklistDdayAssignDto dto) {
        Long memberId = Long.valueOf(dto.getMemberId());
        Checklist checklist = mapper.selectChecklistByMemberId(memberId);

        if (checklist == null) {
            throw new ChecklistNotExistsException(ErrorCode.NOT_EXISTS);
        }

        Checklist checklistWithNewDday = Checklist.withNewDday(checklist, dto);
        mapper.updateChecklist(checklistWithNewDday);

        return checklist.getId();
    }
}
