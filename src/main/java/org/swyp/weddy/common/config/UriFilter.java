package org.swyp.weddy.common.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class UriFilter {

    @Value("${skip.uris}")
    private String skipUris;

    private List<String> skipUriList;

    @PostConstruct
    public void init() {
        this.skipUriList = Arrays.asList(skipUris.split(","));
    }

    public boolean isSkipUri(String uri) {
        return skipUriList.stream()
                .anyMatch(skipUri -> {
                    // 루트 경로(/) 특별 처리
                    if (skipUri.trim().equals("/")) {
                        return uri.equals("/");
                    }
                    return uri.startsWith(skipUri.trim());
                });
    }
}
