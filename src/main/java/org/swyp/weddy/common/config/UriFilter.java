package org.swyp.weddy.common.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class UriFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Value("${skip.uris}")
    private String skipUris;

    private List<String> skipUriList;

    @PostConstruct
    public void init() {
        this.skipUriList = Arrays.asList(skipUris.split(","));
    }

    public boolean isSkipUri(String uri) {
        return skipUriList.stream()
                .map(String::trim)
                .anyMatch(skipUri -> pathMatcher.match(skipUri, uri));
    }
}
