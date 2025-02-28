package org.swyp.weddy.domain.storage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.swyp.weddy.common.exception.ErrorCode;
import org.swyp.weddy.domain.storage.exception.FileDeleteException;
import org.swyp.weddy.domain.storage.exception.FileUploadException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Slf4j
@Service
public class FileStorageService {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final String bucketName;

    public FileStorageService(S3Client s3Client, S3Presigner s3Presigner, @Value("${aws.s3.bucket}") String bucketName) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) {
        try {
            String fileKey = createFileKey(file);
            uploadToS3(file, fileKey);
            return generatePresignedUrl(fileKey);
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.UPLOAD_FILE_FAILED);
        }
    }

    private String createFileKey(MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename();
    }

    private void uploadToS3(MultipartFile file, String fileKey) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();
        s3Client.putObject(putObjectRequest, getRequestBodyForUpload(file));
    }

    private RequestBody getRequestBodyForUpload(MultipartFile file) throws IOException {
        return RequestBody.fromInputStream(file.getInputStream(), file.getSize());
    }

    private String generatePresignedUrl(String fileKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();
        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(
                GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofDays(7))
                        .getObjectRequest(getObjectRequest)
                        .build()
        );
        return presignedRequest.url().toString();
    }

    public void deleteFile(String fileUrl) {
        try {
            String fileKey = extractFileKey(fileUrl);
            deleteToS3(fileKey);
        } catch (Exception e) {
            throw new FileDeleteException(ErrorCode.DELETE_FILE_FAILED);
        }
    }

    private void deleteToS3(String fileKey) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    private String extractFileKey(String fileUrl) throws URISyntaxException {
        URI uri = new URI(fileUrl);
        String path = uri.getPath();
        return path.substring(1);
    }
}