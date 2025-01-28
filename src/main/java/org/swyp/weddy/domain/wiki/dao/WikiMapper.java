package org.swyp.weddy.domain.wiki.dao;

import org.apache.ibatis.annotations.Mapper;
import org.swyp.weddy.domain.wiki.domain.Wiki;

@Mapper
public interface WikiMapper {
    Wiki selectWiki(String title);
}
