package com.study.demo.modules.project.model;

import com.study.demo.modules.file.model.FileResponseMapper;

import java.util.List;
import java.util.UUID;

public record ProjectResponseMapper(UUID id, String name, ProjectVisibility visibility,
                                    List<FileResponseMapper> files) {
    public static ProjectResponseMapper fromEntity(ProjectModel project) {
        return new ProjectResponseMapper(
                project.getId(),
                project.getName(),
                project.getVisibility(),
                null
        );
    }

    public static ProjectResponseMapper fromEntityAndFiles(ProjectModel project) {
        return new ProjectResponseMapper(
                project.getId(),
                project.getName(),
                project.getVisibility(),
                project.getFiles().stream().map(FileResponseMapper::fromEntity).toList()
        );
    }
}
