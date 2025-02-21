package org.swyp.weddy.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Weddy API Document")
                .version("1.0")
                .description(
                        "결혼 준비를 도와주는 웹 애플리케이션 API 문서\n")
                .contact(new io.swagger.v3.oas.models.info.Contact().email("swypteam9@gmail.com"));

        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))
                .addServersItem(new Server().url("https://your-weddy.pe.kr"))
                .info(info);
    }
}
