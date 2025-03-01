package org.swyp.weddy.domain.checklist.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.swyp.weddy.domain.checklist.web.response.ChecklistResponse;

import java.util.Map;

public interface ChecklistApiSpec {

    @Tag(name = "checklist", description = "체크리스트 관련 API")
    @Operation(summary = "체크리스트 생성", description = "새로운 체크리스트를 만든다")
    @PostMapping
    ResponseEntity<Void> createChecklist(
            @Parameter(
                    name = "memberIdMap",
                    description = "사용자 아이디를 갖는 key-value 형식 값",
                    in = ParameterIn.QUERY,
                    required = true,
                    schema = @Schema(type = "object"),
                    style = ParameterStyle.FORM,
                    explode = Explode.TRUE,
                    example = "{ \"memberId\": \"2\" }"
            )
            @RequestBody Map<String, String> memberIdMap
    );

    @Tag(name = "checklist", description = "체크리스트 관련 API")
    @Operation(summary = "체크리스트 검색", description = "사용자 아이디로 체크리스트를 찾는다")
    @GetMapping
    ResponseEntity<ChecklistResponse> getChecklist(
            @Parameter(name = "memberId", description = "사용자 아이디", example = "1", required = true)
            @RequestParam(name = "memberId") String memberId
    );

    @Tag(name = "checklist", description = "체크리스트 관련 API")
    @Operation(summary = "체크리스트 유무 확인", description = "사용자 아이디에 할당된 체크리스트가 있는지 확인한다")
    @GetMapping("/assigned")
    ResponseEntity<Void> hasChecklist(
            @Parameter(name = "memberId", description = "사용자 아이디", example = "1", required = true)
            @RequestParam(name = "memberId") String memberId
    );
}
