package org.swyp.weddy.domain.member.dao;


import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.member.entity.Member;

@Mapper
public interface MemberMapper {
    void saveMember(Member memberInfo);
    int updateMember(Member memberInfo);
    Member selectByMemberId(Long id);

    Member selectByOAuthId(String oAuthId);

}
