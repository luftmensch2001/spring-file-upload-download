package com.file.management.demo.repositories;

import com.file.management.demo.models.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {
}
