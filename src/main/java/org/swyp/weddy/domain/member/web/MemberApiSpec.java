package org.swyp.weddy.domain.member.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.swyp.weddy.domain.member.web.request.MemberEditRequest;
import org.swyp.weddy.domain.member.web.response.MemberResponse;

@Tag(name = "member", description = "사용자 관리 API")
public interface MemberApiSpec {
    @Operation(summary = "사용자 프로필 이미지 수정", description = "특정 사용자의 프로필 이미지 URL을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로필 이미지 수정 성공",
                    content = @Content(schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터", content = @Content),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음", content = @Content),
            @ApiResponse(responseCode = "500", description = "프로필 이미지 수정 실패", content = @Content)
    })
    ResponseEntity<MemberResponse> updateProfileImageUrl(
            @Parameter(description = "사용자 ID", required = true, example = "1")
            @PathVariable(name = "memberId") Long memberId,
            @Parameter(description = "수정할 프로필 이미지 Url", required = true)
            @RequestBody MemberEditRequest request);
}