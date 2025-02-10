package org.swyp.weddy.common.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.auth.exception.JwtTokenExpiredException;
import org.swyp.weddy.domain.auth.exception.JwtUnauthorizedException;
import org.swyp.weddy.domain.auth.service.JwtService;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final String REDIRECT_URL_TO_LOGIN = "http://localhost:8080";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //JWT 검증을 건너뜀
        if (isSkipUri(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        Map<String, String> token = jwtService.resolveToken(request);

        // 인증 실패 시 중단
        if (isAuthenticateFail(token, response)) {
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isSkipUri(String uri) {
        return (uri.startsWith("/h2") |uri.startsWith("/auth/regenerate-token") | uri.startsWith("/auth/login") | uri.startsWith("/index.html") | uri.equals("/"));
    }

    private boolean isAuthenticateFail(Map<String, String> token, HttpServletResponse response) throws IOException {
        try {
            if (jwtService.validateToken(token.get("accessToken"))) {
                Authentication authentication = jwtService.getAuthentication(token.get("accessToken"));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            return false;
        } catch (JwtTokenExpiredException e) {
            log.error("JWT Token has expired", e);
            response.setStatus(ErrorCode.TOKEN_EXPIRED.getCode());
            response.getWriter().write(ErrorCode.TOKEN_EXPIRED.getReason());
            return true; // 인증 실패
        } catch (JwtUnauthorizedException e) {
            log.error("JWT UnAuthorized", e);
            response.sendRedirect(REDIRECT_URL_TO_LOGIN);
            return true; // 인증 실패
        }
    }
}