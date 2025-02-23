package org.swyp.weddy.domain.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.swyp.weddy.domain.auth.service.dto.TokenInfo;

@Service
public class CookieService {

    public void setCookies(HttpServletResponse response, TokenInfo tokenInfo) {
        addCookie(response, "accessToken", tokenInfo.getAccessToken(), "/", 60 * 60 * 24 * 14);
        addCookie(response, "refreshToken", tokenInfo.getRefreshToken(), "/auth/refresh", 60 * 60 * 24 * 365);
    }

    private void addCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite", "None");
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
