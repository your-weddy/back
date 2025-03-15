package org.swyp.weddy.domain.storage.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.swyp.weddy.domain.storage.service.StorageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StorageControllerTest {
    private StorageController storageController;
    private StorageService storageService;
    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        storageController = new StorageController(storageService);
    }

    @DisplayName("파일 업로드를 요청을 받을 수 있다")
    @Test
    void upload_file() {
        //given
        MultipartFile mockMultipartFile = new MockMultipartFile("mock", "test".getBytes());
        when(storageService.uploadFile(mockMultipartFile)).thenReturn("test-url");

        //when
        var result = storageController.uploadFile(mockMultipartFile);

        //then
        assertEquals(200, result.getStatusCodeValue());
        verify(storageService, times(1)).uploadFile(mockMultipartFile);

    }

    @DisplayName("파일 삭제 요청을 받을 수 있다")
    @Test
    void delete_file() {
        //given
        String mockFileUrl = "test-url";
        //when
        storageService.deleteFile(mockFileUrl);
        //then
        verify(storageService, times(1)).deleteFile(mockFileUrl);

    }
}