package com.study.demo.modules.project.mapper;

import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.project.model.ProjectVisibility;

import java.util.UUID;

public record ProjectResponseMapper(UUID id, String name, ProjectVisibility visibility) {
    public static ProjectResponseMapper fromEntity(ProjectModel project) {
        return new ProjectResponseMapper(
                project.getId(),
                project.getName(),
                project.getVisibility()
        );
    }
}
