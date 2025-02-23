package org.swyp.weddy.common.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.swyp.weddy.domain.auth.service.JwtService;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Value("${app.login-page-url}")
    private String LOGIN_PAGE_URL;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Map<String, String> token = jwtService.resolveToken(request);

        if (isValidUser(token)) {
            log.info("=====인증된 사용자======");
            Authentication authentication = jwtService.getAuthentication(token.get("accessToken"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidUser(Map<String, String> token) {
        return token.get("accessToken") != null && jwtService.validateToken(token.get("accessToken"));
    }
}