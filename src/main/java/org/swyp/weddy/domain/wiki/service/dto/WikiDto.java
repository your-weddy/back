package org.swyp.weddy.domain.wiki.service.dto;

import org.swyp.weddy.domain.wiki.web.request.WikiRequest;

public class WikiDto {
    private String title;

    public WikiDto(String title) {
        this.title = title;
    }

    public static WikiDto from(String title) {
        return new WikiDto(title);
    }

    public String getTitle() {
        return title;
    }


}
