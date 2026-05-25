package com.study.demo.modules.file.repository;

import com.study.demo.modules.file.model.File;
import com.study.demo.modules.file.model.FileVersion;
import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileVersionRepository extends JpaRepository<FileVersion, UUID> {
    //Optional<FileVersion> findByFile(File originalFile);
    boolean existsByPath(String path);
    List<FileVersion> findByFile(File file);
}
