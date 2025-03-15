package org.swyp.weddy.domain.storage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.swyp.weddy.domain.storage.exception.FileDeleteException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StorageServiceTest {
    private S3Client s3Client;
    private S3Presigner s3Presigner;
    private String bucketName;
    private StorageService storageService;

    @BeforeEach
    void setUp() {
        s3Client = mock(S3Client.class);
        s3Presigner = mock(S3Presigner.class);
        bucketName = "mockName";
        storageService = new StorageService(s3Client, s3Presigner, bucketName);
    }

    @Nested
    @DisplayName("파일 업로드 테스트")
    class UploadTests {
        @DisplayName("파일 업로드 후 파일주소를 응답할 수 있다.")
        @Test
        void upload_file_and_return_fileurl() throws IOException {
            //given
            //업로드할 파일 모킹
            MockMultipartFile mockFile = new MockMultipartFile(
                    "file",
                    "test.txt",
                    "text/plain",
                    "data".getBytes()
            );

            //s3업로드 성공 모킹
            when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(mock(PutObjectResponse.class));

            //Presigned URL 생성 모킹
            PresignedGetObjectRequest presignedRequest = mock(PresignedGetObjectRequest.class);
            when(presignedRequest.url()).thenReturn(URI.create("https://mock-bucket.s3.amazonaws.com/123_test.txt").toURL());
            when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class))).thenReturn(presignedRequest);

            //when
            String result = storageService.uploadFile(mockFile);

            //then
            assertThat(result).isNotNull();

        }

        @DisplayName("업로드 실패 시 예외처리가 발생한다.")
        @Test
        void throws_exception_if_s3_upload_failed() {
            // given
            String fileUrl = "https://mock-bucket.s3.amazonaws.com/123_test.txt";
            when(s3Client.deleteObject(any(DeleteObjectRequest.class)))
                    .thenThrow(new RuntimeException("S3 delete failed"));

            // when, then
            assertThatThrownBy(() -> storageService.deleteFile(fileUrl))
                    .isInstanceOf(FileDeleteException.class) ;
            verify(s3Client, times(1)).deleteObject(any(DeleteObjectRequest.class));
        }
    }

    @Nested
    @DisplayName("파일 삭제 테스트")
    class DeleteTests {
        @DisplayName("파일을 성공적으로 삭제한다")
        @Test
        void delete_file() {
            // given
            String fileUrl = "https://mock-bucket.s3.amazonaws.com/123_test.txt";
            when(s3Client.deleteObject(any(DeleteObjectRequest.class))).thenReturn(null);

            // when
            storageService.deleteFile(fileUrl);

            // then
            verify(s3Client, times(1)).deleteObject(any(DeleteObjectRequest.class));
        }

        @DisplayName("파일 삭제 실패 시 예외처리가 발생한다")
        @Test
        void throws_exception_if_s3_delete_failed() {
            // given
            String fileUrl = "https://mock-bucket.s3.amazonaws.com/123_test.txt";
            when(s3Client.deleteObject(any(DeleteObjectRequest.class)))
                    .thenThrow(new RuntimeException("S3 delete failed"));

            // when, then
            assertThatThrownBy(()-> storageService.deleteFile(fileUrl))
                    .isInstanceOf(FileDeleteException.class);
            verify(s3Client, times(1)).deleteObject(any(DeleteObjectRequest.class));
        }
    }
}