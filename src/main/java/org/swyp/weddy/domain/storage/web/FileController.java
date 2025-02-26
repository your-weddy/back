package org.swyp.weddy.domain.storage.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.swyp.weddy.domain.storage.service.FileStorageService;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileStorageService storageService;

    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = storageService.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam("fileUrl") String fileUrl) {
        try {
            storageService.deleteFile(fileUrl);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File deletion failed: " + e.getMessage());
        }
    }
}