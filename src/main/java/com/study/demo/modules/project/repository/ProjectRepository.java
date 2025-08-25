package com.study.demo.modules.project.repository;

import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByWorkspace(WorkspaceModel workspace);
    Optional<Project> findOneByNameAndWorkspace(String name, WorkspaceModel workspace);
}
