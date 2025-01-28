package org.swyp.weddy.domain.wiki.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swyp.weddy.domain.wiki.service.WikiService;
import org.swyp.weddy.domain.wiki.service.dto.WikiDto;
import org.swyp.weddy.domain.wiki.web.response.WikiResponse;

@RestController
@RequestMapping("/wiki")
public class WikiController {

    private final WikiService wikiService;

    public WikiController(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    @GetMapping
    public ResponseEntity<WikiResponse> getWiki(@RequestParam(name = "title") String title) {
        WikiResponse wikiResponse = wikiService.findWiki(WikiDto.from(title));
        return ResponseEntity.ok().body(wikiResponse);
    }
}
