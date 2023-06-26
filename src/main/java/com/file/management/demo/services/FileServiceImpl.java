package com.file.management.demo.services;

import com.file.management.demo.models.FileEntity;
import com.file.management.demo.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public Iterable<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public FileEntity getFileById(Integer id) {
        Optional<FileEntity> foundFile = fileRepository.findById(id);
        if (foundFile.isPresent())
            return foundFile.get();
        else
            return null;
    }

    @Override
    public FileEntity uploadFile(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileEntity newFile = new FileEntity(fileName, file.getContentType(), file.getBytes());
            return fileRepository.save(newFile);
        }
        catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Upload file failed");
        }
    }

    @Override
    public ResponseEntity<?> downloadFile(Integer id) {
        FileEntity file = getFileById(id);
        if (file == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(new ByteArrayResource(file.getData()));
    }

    @Override
    public void deleteFile(Integer id) {
        fileRepository.deleteById(id);
    }
}
