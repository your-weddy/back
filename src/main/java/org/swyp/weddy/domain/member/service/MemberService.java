package org.swyp.weddy.domain.member.service;

import org.swyp.weddy.domain.member.service.dto.MemberEditDto;

public interface MemberService {

    void updateProfileImage(MemberEditDto dto);
}
