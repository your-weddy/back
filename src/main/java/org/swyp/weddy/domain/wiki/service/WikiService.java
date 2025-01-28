package org.swyp.weddy.domain.wiki.service;

import org.springframework.stereotype.Service;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.wiki.dao.WikiMapper;
import org.swyp.weddy.domain.wiki.domain.Wiki;
import org.swyp.weddy.domain.wiki.exception.WikiNotFoundException;
import org.swyp.weddy.domain.wiki.service.dto.WikiDto;
import org.swyp.weddy.domain.wiki.web.response.WikiResponse;

@Service
public class WikiService {

    private final WikiMapper wikiMapper;

    public WikiService(WikiMapper wikiMapper) {
        this.wikiMapper = wikiMapper;
    }

    public WikiResponse findWiki(WikiDto dto) {
        String title = dto.getTitle();
        Wiki wiki = wikiMapper.selectWiki(title);

        if (wiki == null) {
            throw new WikiNotFoundException(ErrorCode.NOT_EXISTS);
        }

        return WikiResponse.of(wiki);
    }
}
