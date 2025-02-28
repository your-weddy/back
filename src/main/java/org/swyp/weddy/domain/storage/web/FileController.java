package org.swyp.weddy.domain.storage.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swyp.weddy.domain.storage.service.FileStorageService;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileStorageService storageService;

    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            String fileUrl = storageService.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam("fileUrl") String fileUrl) {
            storageService.deleteFile(fileUrl);
            return ResponseEntity.ok().build();
    }
}