package org.swyp.weddy.domain.wiki.web.response;

import org.swyp.weddy.domain.wiki.domain.Wiki;

public class WikiResponse {
    private String title;

    public WikiResponse(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static WikiResponse of(Wiki wiki) {
        return new WikiResponse(wiki.getTitle());
    }
}
