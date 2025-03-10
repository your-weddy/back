package org.swyp.weddy.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.*;
import org.springframework.security.core.Authentication;
import org.swyp.weddy.domain.auth.service.dto.KakaoUserInfo;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;
import org.swyp.weddy.domain.member.dao.MemberMapper;
import org.swyp.weddy.domain.member.entity.Member;

import java.util.Map;

class AuthServiceTest {
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        authService = new AuthService(
                new FakeOAuth2Service(),
                new FakeMemberMapper(),
                new FakeJwtService()
        );
    }

    @Nested
    class ProcessKakaoLoginTest {
        @DisplayName("카카오 서버로부터 엑세스 토큰을 가져오지 못했을 때")
        @Test
        public void fail_to_retrieve_access_token_from_kakao_server() {
            Assertions.assertThrows(RuntimeException.class, () -> {
                authService.processKakaoLogin("access_token_retrieve_fail");
            });
        }

        @DisplayName("카카오 서버로부터 사용자 정보를 가져오지 못했을 때")
        @Test
        public void fail_to_retrieve_user_info_from_kakao() {
            Assertions.assertThrows(RuntimeException.class, () -> {
                authService.processKakaoLogin("access_token_retrieve_fail");
            });
        }

        @DisplayName("사용자 정보 DB에 저장에 실패했을 때")
        @Test
        public void fail_to_save_user_into_db() {
            Assertions.assertThrows(RuntimeException.class, () -> {
                authService.processKakaoLogin("save_user_to_db_fail");
            });
        }
    }

    private static class FakeOAuth2Service implements OAuth2Service {
        @Override
        public String getAccessToken(String code) {
            if (code.equals("login_success")) {
                return "access_token";
            } else if (code.equals("save_user_to_db_fail")) {
                return "access_token.db_fail";
            }
            throw new RuntimeException("fail_to_retrieve_access_token");
        }

        @Override
        public KakaoUserInfo getUserInfo(String accessToken) {
            if (accessToken.equals("access_token")) {
                return KakaoUserInfo.builder()
                        .id(1)
                        .oAuthId(1L)
                        .email("x@y")
                        .nickname("t")
                        .build();
            }
            if (accessToken.equals("access_token.db_fail")) {
                return KakaoUserInfo.builder()
                        .id(1)
                        .oAuthId(1L)
                        .email("x@y")
                        .nickname("db_fail")
                        .build();
            }
            throw new RuntimeException("fail_to_retrieve_user_info");
        }
    }

    private static class FakeMemberMapper implements MemberMapper {

        @Override
        public void saveMember(Member memberInfo) {
            if ("db_fail".equals(memberInfo.getName())) {
                throw new RuntimeException("fail_to_save_member");
            }
        }

        @Override
        public int updateMember(Member memberInfo) {
            return 0;
        }

        @Override
        public Member selectByMemberId(Long id) {
            return null;
        }

        @Override
        public Member selectByOAuthId(String oAuthId) {
            return null;
        }
    }

    private static class FakeJwtService implements JwtService {

        @Override
        public TokenInfo generateToken(Authentication authentication) {
            return null;
        }

        @Override
        public Authentication getAuthentication(String accessToken) {
            return null;
        }

        @Override
        public boolean validateToken(String token) {
            return false;
        }

        @Override
        public Map<String, String> resolveToken(HttpServletRequest request) {
            return Map.of();
        }
    }
}
