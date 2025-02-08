package org.swyp.weddy.domain.checklist.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.checklist.service.ChecklistService;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/checklist")
public class ChecklistController {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping
    public ResponseEntity<Void> createChecklist(@RequestBody Map<String, String> memberIdMap) {
        int insertId = checklistService.assignChecklist(ChecklistDto.from(memberIdMap.get("memberId")));
        log.warn("---------------------");
        log.warn("insertId: " + insertId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> getChecklist(@RequestParam(name = "memberId") String memberId) {
        ChecklistDto dto = ChecklistDto.from(memberId);
        boolean hasChecklist = checklistService.hasChecklist(dto);
        log.warn("---------------------");
        log.warn("hasChecklist: " + hasChecklist);

        return ResponseEntity.ok().build();
    }
}
