package org.swyp.weddy.domain.checklist.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDdayAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;
import org.swyp.weddy.domain.checklist.web.request.ChecklistCreateRequest;
import org.swyp.weddy.domain.checklist.web.request.ChecklistDdayAssignRequest;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

@Slf4j
@RestController
@RequestMapping("/checklist")
public class ChecklistController implements ChecklistApiSpec {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping
    public ResponseEntity<Void> createChecklist(@RequestBody ChecklistCreateRequest request) {
        Long insertId = checklistService.assignChecklist(ChecklistDto.fromCreateRequest(request));
        log.warn("---------------------");
        log.warn("insertId: " + insertId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ChecklistResponse> getChecklist(@RequestParam(name = "memberId") String memberId) {
        ChecklistDto dto = ChecklistDto.from(memberId);
        ChecklistResponse checklist = checklistService.findChecklist(dto);

        return ResponseEntity.ok().body(checklist);
    }

    @GetMapping("/assigned")
    public ResponseEntity<Void> hasChecklist(@RequestParam(name = "memberId") String memberId) {
        ChecklistDto dto = ChecklistDto.from(memberId);
        boolean hasChecklist = checklistService.hasChecklist(dto);
        log.warn("---------------------");
        log.warn("hasChecklist: " + hasChecklist);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/d-day")
    public ResponseEntity<Void> assignWeddingDate(@RequestBody ChecklistDdayAssignRequest request) {
        ChecklistDdayAssignDto dto = ChecklistDdayAssignDto.from(request);
        checklistService.editDday(dto);
        return ResponseEntity.ok().build();
    }
}
