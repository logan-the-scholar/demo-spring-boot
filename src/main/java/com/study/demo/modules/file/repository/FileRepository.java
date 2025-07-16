package com.study.demo.modules.file.repository;

import com.study.demo.modules.file.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileModel, UUID> {
}
