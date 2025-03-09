package org.swyp.weddy.domain.checklist.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemDeleteRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemEditRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemMoveRequest;
import org.swyp.weddy.domain.checklist.web.request.LargeCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.LargeCatItemResponse;

import java.util.List;

public interface LargeCatApiSpec {

    @GetMapping("/{id}")
    ResponseEntity<LargeCatItemResponse> getItem(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "memberId") String memberId
    );

    @Tag(name = "large-category", description = "대분류 항목 관련 API")
    @Operation(summary = "대분류 항목 가져오기", description = "조건에 맞는 모든 대분류 항목을 가져온다. memberId만 보낼 경우, 모든 대분류 항목을 가져온다.\n만약 진행 상황과 담당자 필터링 조건을 동시에 보낼 경우, 모든 조건을 만족하는 항목을 가져온다")
    @GetMapping
    ResponseEntity<List<LargeCatItemResponse>> getAllItems(
            @Parameter(name = "memberId", description = "사용자 아이디", example = "1", required = true)
            @RequestParam(name = "memberId") String memberId,
            @Parameter(name = "itemStatuses", description = "필터링 조건으로 쓸 진행 상황. 쉼표로 구분해 여러 개를 보낼 수 있다", example = "시작전,진행중", required = false)
            @RequestParam(name = "itemStatuses", required = false, defaultValue = "") String itemStatuses,
            @Parameter(name = "itemAssignees", description = "필터링 조건으로 쓸 담당자. 쉼표로 구분해 여러 개를 보낼 수 있다", example = "신랑,신부", required = false)
            @RequestParam(name = "itemAssignees", required = false, defaultValue = "") String itemAssignees
    );

    @Tag(name = "large-category", description = "대분류 항목 관련 API")
    @Operation(summary = "대분류 항목 만들기", description = "새로운 대분류 항목을 만든다")
    @PostMapping
    ResponseEntity<Void> postItem(
            @Parameter(name = "LargeCatItemPostRequest", description = "새로 만들 대분류 항목을 가질 사용자 아이디, 만들어질 대분류 항목의 제목", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "someExample1", value = """
                                        {
                                            "memberId" : "1",
                                            "title": "new-title"
                                        }
                                    """)
                    }
            ))
            @RequestBody LargeCatItemPostRequest request
    );

    @Tag(name = "large-category", description = "대분류 항목 관련 API")
    @Operation(summary = "대분류 항목 수정", description = "기존 대분류 항목의 제목을 수정한다")
    @PatchMapping
    ResponseEntity<Void> patchItem(
            @Parameter(name = "LargeCatItemEditRequest", description = "사용자 아이디, 수정할 대분류 항목의 아이디 및 제목", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "someExample1", value = """
                                        {
                                            "memberId" : "1",
                                            "id": "1",
                                            "editedTitle": "title-to-edit"
                                        }
                                    """)
                    }
            ))
            @RequestBody LargeCatItemEditRequest request
    );

    @Tag(name = "large-category", description = "대분류 항목 관련 API")
    @Operation(summary = "대분류 항목 삭제", description = "대분류 항목을 지운다")
    @PatchMapping("/delete")
    ResponseEntity<Void> deleteItem(
            @Parameter(name = "LargeCatItemDeleteRequest", description = "사용자 아이디와 삭제할 대분류 항목의 아이디", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "someExample1", value = """
                                        {
                                            "memberId" : "1",
                                            "id": "1"
                                        }
                                    """)
                    }
            ))
            @RequestBody LargeCatItemDeleteRequest request
    );

    @Tag(name = "large-category", description = "대분류 항목 관련 API")
    @Operation(summary = "대분류 항목 위치 변경", description = "웹 페이지에 보이는 대분류 항목의 위치를 변경한다")
    @PatchMapping("/move")
    ResponseEntity<Void> moveItem(
            @Parameter(name = "LargeCatItemMoveRequest", description = "사용자 아이디와 수정된 항목의 순서", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "someExample1", value = """
                                        {
                                            "memberId" : "1",
                                            "idSequence": [3,1,2]
                                        }
                                    """)
                    }
            ))
            @RequestBody LargeCatItemMoveRequest request
    );
}
