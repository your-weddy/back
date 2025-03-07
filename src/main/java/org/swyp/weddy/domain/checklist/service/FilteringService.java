package org.swyp.weddy.domain.checklist.service;

import org.swyp.weddy.domain.checklist.service.dto.FilterByStatusDto;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

public interface FilteringService {
    List<LargeCatItemResponse> filterByStatus(FilterByStatusDto dto);
}
