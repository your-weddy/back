package org.swyp.weddy.domain.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.auth.exception.JwtTokenExpiredException;
import org.swyp.weddy.domain.auth.exception.JwtUnauthorizedException;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtService {

    private final String secretKeyPlain;
    private SecretKey secretKeyForSign;
    private final long ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS;
    private final long REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS;

    public JwtService(@Value("${jwt.secret}") String secretKeyPlain, @Value("${jwt.access-token-duration}") long accessTokenDuration, @Value("${jwt.refresh-token-duration}") long refreshTokenDuration) {
        this.secretKeyPlain = secretKeyPlain;
        this.ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS = accessTokenDuration;
        this.REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS = refreshTokenDuration;
    }

    @PostConstruct
    private void setSecretKey() {
        secretKeyForSign = Keys.hmacShaKeyFor(secretKeyPlain.getBytes());
    }

    public TokenInfo generateToken(Authentication authentication) {
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime() + ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS);
        Date refreshTokenExpiresIn = new Date(now.getTime() + REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS);

        return new TokenInfo(
                "Bearer",
                buildJwtToken(authentication, accessTokenExpiresIn),
                buildJwtToken(authentication, refreshTokenExpiresIn));
    }

    private String buildJwtToken(Authentication authentication, Date expiresIn) {
        Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();

        return Jwts.builder()
                .subject(authentication.getName())
                .claim("id", principal.get("id"))
                .claim("email", principal.get("email"))
                .claim("auth", Collections.emptyList())
                .issuedAt(new Date())
                .expiration(expiresIn)
                .signWith(secretKeyForSign, Jwts.SIG.HS512)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Map<String, Object> principal = Map.of(
                "id", claims.get("id", Integer.class),
                "email", claims.get("email", String.class)
        );

        return new UsernamePasswordAuthenticationToken(principal, "", Collections.emptyList());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKeyForSign)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            throw new JwtTokenExpiredException(ErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            throw new JwtUnauthorizedException(ErrorCode.UNAUTHORIZED);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKeyForSign)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Map<String, String> resolveToken(HttpServletRequest request) {
        Map<String, String> token = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    token.put("accessToken", cookie.getValue());
                } else if ("refreshToken".equals(cookie.getName())) {
                    token.put("refreshToken", cookie.getValue());
                }
            }
        }
        return token;
    }
}