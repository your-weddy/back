package org.swyp.weddy.domain.member.service;

import org.springframework.stereotype.Service;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.member.dao.MemberMapper;
import org.swyp.weddy.domain.member.entity.Member;
import org.swyp.weddy.domain.member.exception.MemberUpdateException;
import org.swyp.weddy.domain.member.service.dto.MemberEditDto;

@Service
public class MemberServiceImpl implements MemberService{

    MemberMapper mapper;

    public MemberServiceImpl(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void updateProfileImage(MemberEditDto memberEditDto) {
        Member memberBeforeEdit = mapper.selectByMemberId(memberEditDto.getId());

        if (memberBeforeEdit == null) {
            throw new MemberNotFoundException(ErrorCode.NOT_EXISTS);
        }

        Member member = Member.ofEdit(memberBeforeEdit, memberEditDto);
        int updatedRows = mapper.updateMember(member);

        if (updatedRows != 1){
            throw new MemberUpdateException(ErrorCode.UPDATE_FAILED);
        }
    }
}
