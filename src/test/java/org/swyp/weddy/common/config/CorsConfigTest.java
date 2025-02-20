package org.swyp.weddy.common.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigTest {

    @DisplayName("cors 설정에 필요한 객체를 생성할 수 있다")
    @Test
    public void make_cors_config_bean() {
        CorsConfig corsConfig = new CorsConfig("http://localhost:3000");
        CorsConfigurationSource corsConfigSrc = corsConfig.corsConfigSource();
        assertNotNull(corsConfigSrc);
    }

}