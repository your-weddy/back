package org.swyp.weddy.domain.auth.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.swyp.weddy.domain.auth.exception.MemberNotFoundException;
import org.swyp.weddy.domain.auth.web.response.AuthResponse;

import java.io.IOException;

@Tag(name = "auth", description = "인증 및 사용자 관리 API")
public interface AuthApiSpec {
    @Operation(summary = "카카오 로그인 콜백(프론트에서 호출X)", description = "카카오 OAuth2 인증 코드를 받아 로그인 처리를 하고 홈페이지로 리다이렉트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "로그인 성공 후 홈페이지로 리다이렉트", content = @Content)
    })
    void kakaoCallback(
            @Parameter(description = "카카오 인가 코드", required = true, example = "abc123")
            @RequestParam("code") String code,
            @Parameter(hidden = true) HttpServletResponse response) throws IOException;

    @Operation(summary = "토큰 갱신", description = "리프레시 토큰을 사용해 새로운 액세스 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 갱신 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content)
    })
    ResponseEntity<Void> refreshToken(
            @Parameter(hidden = true) HttpServletRequest request,
            @Parameter(hidden = true) HttpServletResponse response);

    @Operation(summary = "현재 사용자 정보 조회", description = "인증된 사용자의 정보를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
                    content = @Content(schema = @Schema(implementation = MemberNotFoundException.class)))
    })
    ResponseEntity<AuthResponse> getAuthInfo();

    @Operation(summary = "로그아웃", description = "현재 사용자의 쿠키를 삭제하여 로그아웃처리 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공", content = @Content),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
                    content = @Content(schema = @Schema(implementation = MemberNotFoundException.class)))
    })
    ResponseEntity<Void> logout(
            @Parameter(hidden = true) HttpServletResponse response);
}