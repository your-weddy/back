package org.swyp.weddy.domain.wiki.web.request;

public class WikiRequest {
    private String title;

    public WikiRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
