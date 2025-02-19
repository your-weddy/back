package org.swyp.weddy.domain.auth.dao;


import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.auth.entity.Member;

@Mapper
public interface MemberMapper {
    void saveMember(Member memberInfo);
    void updateMember(Member memberInfo);
    Member findByMemberId(Long id);

    Member findByOAuthId(String oAuthId);

}
