package org.swyp.weddy.domain.smallcategory.service;

import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.res.SmallCatItemResponse;

import java.util.List;

public interface SmallCatService {
    //내부용 인터페이스
    List<SmallCatItemResponse> findItems(Long checkListId, Long largeCatItemId); //Long checklistId, Long largeCatId);
    boolean deleteAll(Long checkListId, Long largeCatItemId);  //Long checklistId, Long largeCatId,

    //외부용 인터페이스
    SmallCatItemResponse findDetails(SmallCatItemDto dto); //Long checklistId, Long largeCatId, Long smallCatId);
    Long assignItem(SmallCatItemDto dto);//Long checklistId, Long largeCatId
    boolean edit(SmallCatItemDto dto); //Long checklistId, Long largeCatId, Long smallCatId);
    boolean delete(SmallCatItemDto dto); //Long checklistId, Long largeCatId, Long smallCatId);

}