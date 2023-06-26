package com.file.management.demo.services;

import com.file.management.demo.models.FileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileService {
    public Iterable<FileEntity> getAllFiles();
    public FileEntity getFileById(Integer id);
    public FileEntity uploadImage(MultipartFile file);
    public ResponseEntity<?> downloadFile(Integer id);
    public void deleteFile(Integer id);
}
