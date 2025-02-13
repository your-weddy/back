package org.swyp.weddy.domain.checklist.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.LargeCatService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemEditDto;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemDeleteRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemEditRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/checklist/large-cat-item")
public class LargeCatController {
    private final LargeCatService largeCatService;
    private final ChecklistService checklistService;

    public LargeCatController(LargeCatService largeCatService, ChecklistService checklistService) {
        this.largeCatService = largeCatService;
        this.checklistService = checklistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LargeCatItemResponse> getItem(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "memberId") String memberId
    ) {
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistResponse checklist = checklistService.findChecklist(dto);

        Long checklistId = checklist.getId();
        LargeCatItemResponse item = largeCatService.findItemWithSmallItems(checklistId, Long.valueOf(id));

        return ResponseEntity.ok().body(item);
    }

    public ResponseEntity<List<LargeCatItemResponse>> getAllItems(@RequestParam(name = "memberId") String memberId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Void> postItem(@RequestBody LargeCatItemPostRequest request) {
        String memberId = request.getMemberId();
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistResponse checklist = checklistService.findChecklist(dto);

        LargeCatItemAssignDto assignDto = new LargeCatItemAssignDto(checklist.getId(), request.getTitle());
        largeCatService.addItem(assignDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> patchItem(@RequestBody LargeCatItemEditRequest request) {
        String memberId = request.getMemberId();
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistResponse checklist = checklistService.findChecklist(dto);

        LargeCatItemEditDto editDto = LargeCatItemEditDto.of(checklist.getId(), request);
        largeCatService.editItem(editDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/delete")
    public ResponseEntity<Void> deleteItem(@RequestBody LargeCatItemDeleteRequest request) {
        String memberId = request.getMemberId();
        ChecklistDto dto = ChecklistDto.from(memberId);

        ChecklistResponse checklist = checklistService.findChecklist(dto);

        LargeCatItemDeleteDto deleteDto = LargeCatItemDeleteDto.of(checklist.getId(), request);
        largeCatService.deleteItem(deleteDto);

        return ResponseEntity.ok().build();
    }
}
