package org.swyp.weddy.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;

import java.util.Map;

public interface JwtService {
    TokenInfo generateToken(Authentication authentication);

    Authentication getAuthentication(String accessToken);

    boolean validateToken(String token);

    Map<String, String> resolveToken(HttpServletRequest request);
}
