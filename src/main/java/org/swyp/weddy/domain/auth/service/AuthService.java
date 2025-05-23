package org.swyp.weddy.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.member.dao.MemberMapper;
import org.swyp.weddy.domain.member.entity.Member;
import org.swyp.weddy.domain.auth.exception.JwtRefreshTokenInvalidException;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;
import org.swyp.weddy.domain.auth.web.response.AuthResponse;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final OAuth2Service oAuth2Service;
    private final MemberMapper memberMapper;
    private final JwtService jwtService;

    public TokenInfo processKakaoLogin(String code) {
        String accessToken = oAuth2Service.getAccessToken(code);

        KakaoUserInfo kakaoUserInfo = oAuth2Service.getUserInfo(accessToken);

        Member member = Member.from(kakaoUserInfo);
        saveDatabase(member);

        Authentication authentication = createAuthentication(member);

        TokenInfo tokenInfo = jwtService.generateToken(authentication);

        return tokenInfo;
    }

    private void saveDatabase(Member member) {
        Member existingUser = memberMapper.selectByOAuthId(member.getOAuthId());
        if (existingUser == null) {
            memberMapper.saveMember(member);
        }
    }

    private Authentication createAuthentication(Member member) {
        Member memberInfo = memberMapper.selectByOAuthId(member.getOAuthId());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                Map.of(
                        "id", memberInfo.getId(),
                        "email", memberInfo.getEmail()
                ),
                "",
                Collections.emptyList()
        );
        return authentication;
    }

    public TokenInfo generateNewTokenIfRefreshTokenValid(String refreshToken) {
        if (!validateToken(refreshToken)) {
            throw new JwtRefreshTokenInvalidException(ErrorCode.TOKEN_INVALID);
        }

        Authentication authentication = jwtService.getAuthentication(refreshToken);
        return jwtService.generateToken(authentication);
    }

    private boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }


    public Map<String, String> resolveToken(HttpServletRequest request) {
        return jwtService.resolveToken(request);
    }

    public boolean isValidMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        return true;
    }

    public AuthResponse getAuthInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();
        Number id = (Number) principal.get("id");
        Member member = memberMapper.selectByMemberId(id.longValue());

        if (member == null) {
            throw new MemberNotFoundException(ErrorCode.NOT_EXISTS);
        }

        return AuthResponse.from(member);
    }
}