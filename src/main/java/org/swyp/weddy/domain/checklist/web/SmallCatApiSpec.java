package org.swyp.weddy.domain.checklist.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemMoveRequest;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPatchRequest;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.util.List;

@Tag(name = "small-category", description = "소분류 항목 관리 API")
public interface SmallCatApiSpec {

    @Operation(summary = "소분류 항목 목록 조회", description = "특정 체크리스트와 대분류에 속한 소분류 항목 미리보기 목록을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "목록 조회 성공", content = @Content(schema = @Schema(implementation = SmallCatItemPreviewResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터", content = @Content),
    })
    ResponseEntity<List<SmallCatItemPreviewResponse>> getSmallCatItemList(
            @Parameter(description = "체크리스트 ID", required = true, example = "1") Long checklistId,
            @Parameter(description = "대분류 항목 ID", required = true, example = "1") Long largeCatItemId);

    @Operation(summary = "소분류 항목 상세 조회", description = "특정 소분류 항목의 상세 정보를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상세 조회 성공", content = @Content(schema = @Schema(implementation = SmallCatItemResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터", content = @Content),
            @ApiResponse(responseCode = "404", description = "항목을 찾을 수 없음", content = @Content)
    })
    ResponseEntity<SmallCatItemResponse> getSmallCatItem(
            @Parameter(description = "체크리스트 ID", required = true, example = "1") Long checklistId,
            @Parameter(description = "대분류 항목 ID", required = true, example = "1") Long largeCatItemId,
            @Parameter(description = "소분류 항목 ID", required = true, example = "1") Long smallCatItemId);

    @Operation(summary = "소분류 항목 추가", description = "새로운 소분류 항목을 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "항목 추가 성공", content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터", content = @Content),
            @ApiResponse(responseCode = "500", description = "항목 추가 실패", content = @Content)
    })
    ResponseEntity<Long> postItem(
            @Parameter(description = "소분류 항목 추가 요청 데이터", required = true) SmallCatItemPostRequest request);

    @Operation(summary = "소분류 항목 수정", description = "기존 소분류 항목을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터", content = @Content),
            @ApiResponse(responseCode = "404", description = "항목을 찾을 수 없음", content = @Content)
    })
    ResponseEntity<Boolean> patchItem(
            @Parameter(description = "소분류 항목 수정 요청 데이터", required = true) SmallCatItemPatchRequest request);

    @Operation(summary = "소분류 항목 삭제", description = "특정 소분류 항목을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터", content = @Content),
            @ApiResponse(responseCode = "500", description = "항목 삭제 실패", content = @Content)
    })
    ResponseEntity<Boolean> deleteItem(
            @Parameter(description = "체크리스트 ID", required = true, example = "1") Long checklistId,
            @Parameter(description = "대분류 항목 ID", required = true, example = "1") Long largeCatItemId,
            @Parameter(description = "소분류 항목 ID", required = true, example = "1") Long smallCatItemId);

    @Operation(summary = "모든 소분류 항목 삭제", description = "특정 대분류에 속한 모든 소분류 항목을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전체 삭제 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터", content = @Content),
            @ApiResponse(responseCode = "500", description = "항목 삭제 실패", content = @Content)
    })
    ResponseEntity<Boolean> deleteAllItems(
            @Parameter(description = "체크리스트 ID", required = true, example = "1") Long checklistId,
            @Parameter(description = "대분류 항목 ID", required = true, example = "1") Long largeCatItemId);

    @Operation(summary = "소분류 항목 이동", description = "소분류 항목의 위치를 이동합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이동 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터", content = @Content),
            @ApiResponse(responseCode = "404", description = "항목을 찾을 수 없음", content = @Content)
    })
    ResponseEntity<Boolean> moveItem(
            @Parameter(
                    description = "소분류 항목 이동 요청 데이터. 아래 필드를 포함합니다:" +
                            "<ul>" +
                            "<li><b>checklistId</b>: 소분류 항목이 속한 체크리스트 ID (Long)</li>" +
                            "<li><b>largeCatItemId</b>: 소분류 항목 이동 후 속하게 될 대분류 항목의 ID (Long)</li>" +
                            "<li><b>smallCatItemIds</b>: 대분류 항목 내 모든 소분류 항목 ID를 순서대로 담은 배열 (List&lt;Long&gt;)</li>" +
                            "</ul>",
                    required = true,
                    schema = @Schema(implementation = SmallCatItemMoveRequest.class)
            ) SmallCatItemMoveRequest request);

}