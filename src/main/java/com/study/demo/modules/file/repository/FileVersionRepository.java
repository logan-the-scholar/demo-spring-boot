package com.study.demo.modules.file.repository;

import com.study.demo.modules.file.model.FileVersionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileVersionRepository extends JpaRepository<FileVersionModel, UUID> {
}
