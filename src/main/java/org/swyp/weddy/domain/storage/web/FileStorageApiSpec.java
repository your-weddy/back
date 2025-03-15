package org.swyp.weddy.domain.storage.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "file-storage", description = "파일 저장소 API")
public interface FileStorageApiSpec {

    @Operation(summary = "파일 업로드", description = "새로운 파일을 업로드하고 파일 URL을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 업로드 성공",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 파일 형식", content = @Content),
            @ApiResponse(responseCode = "500", description = "파일 업로드 실패", content = @Content)
    })
    ResponseEntity<String> uploadFile(
            @Parameter(description = "업로드할 파일", required = true)
            @RequestParam("file") MultipartFile file);

    @Operation(summary = "파일 삭제", description = "지정된 URL의 파일을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 삭제 성공",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 파일 URL", content = @Content),
            @ApiResponse(responseCode = "500", description = "파일 삭제 실패", content = @Content)
    })
    ResponseEntity<String> deleteFile(
            @Parameter(description = "삭제할 파일의 URL", required = true, example = "https://example.com/file.jpg")
            @RequestParam("fileUrl") String fileUrl);
}