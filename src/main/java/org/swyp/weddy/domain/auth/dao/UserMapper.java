package org.swyp.weddy.domain.auth.dao;


import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;

@Mapper
public interface UserMapper {
    void saveUser(KakaoUserInfo userInfo);
    void updateUser(KakaoUserInfo userInfo);
    KakaoUserInfo findByKakaoEmail(String email);
}
