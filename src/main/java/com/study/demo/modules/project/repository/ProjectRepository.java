package com.study.demo.modules.project.repository;

import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
    List<ProjectModel> findByWorkspace(WorkspaceModel workspace);
}
