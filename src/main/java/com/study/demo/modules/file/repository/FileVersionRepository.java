package com.study.demo.modules.file.repository;

import com.study.demo.modules.file.model.File;
import com.study.demo.modules.file.model.FileVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileVersionRepository extends JpaRepository<FileVersion, UUID> {
    Optional<FileVersion> findByFile(File originalFile);
    boolean existsByPath(String path);
}
