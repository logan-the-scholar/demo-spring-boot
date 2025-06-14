package com.study.demo.modules.project.repository;

import com.study.demo.modules.project.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {

}
