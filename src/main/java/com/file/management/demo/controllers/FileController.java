package com.file.management.demo.controllers;

import com.file.management.demo.models.FileEntity;
import com.file.management.demo.repositories.FileRepository;
import com.file.management.demo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class FileController {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileService fileService;

    @GetMapping("/getAllFile")
    public ResponseEntity<?> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @GetMapping("/getFileById/{id}")
    public ResponseEntity<FileEntity> getFileById(@PathVariable Integer id) {
        FileEntity foundFile = fileService.getFileById(id);
        return foundFile != null ?
                ResponseEntity.ok(foundFile) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id) {
        return fileService.downloadFile(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }
}
