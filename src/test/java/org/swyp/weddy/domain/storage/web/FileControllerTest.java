package org.swyp.weddy.domain.storage.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.swyp.weddy.domain.storage.service.FileStorageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FileControllerTest {
    FileController fileController;
    FileStorageService fileStorageService;
    @BeforeEach
    void setUp() {
        fileStorageService = mock(FileStorageService.class);
        fileController = new FileController(fileStorageService);
    }

    @DisplayName("파일 업로드를 요청을 받을 수 있다")
    @Test
    void upload_file() {
        //given
        MultipartFile mockMultipartFile = new MockMultipartFile("mock", "test".getBytes());
        when(fileStorageService.uploadFile(mockMultipartFile)).thenReturn("test-url");

        //when
        var result = fileController.uploadFile(mockMultipartFile);

        //then
        assertEquals(200, result.getStatusCodeValue());
        verify(fileStorageService, times(1)).uploadFile(mockMultipartFile);

    }

    @DisplayName("파일 삭제 요청을 받을 수 있다")
    @Test
    void delete_file() {
        //given
        String mockFileUrl = "test-url";
        //when
        fileStorageService.deleteFile(mockFileUrl);
        //then
        verify(fileStorageService, times(1)).deleteFile(mockFileUrl);

    }
}