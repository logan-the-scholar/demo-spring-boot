package com.study.demo.modules.project.model;

import com.study.demo.modules.branch.model.Branch;

import java.util.List;
import java.util.UUID;

public record ProjectResponseMapper(UUID id, String name, ProjectVisibility visibility, List<String> branches) {
    public static ProjectResponseMapper fromEntity(Project project) {
        return new ProjectResponseMapper(
                project.getId(),
                project.getName(),
                project.getVisibility(),
                null
        );
    }

    public static ProjectResponseMapper fromEntityAndBranches(Project project) {
        return new ProjectResponseMapper(
                project.getId(),
                project.getName(),
                project.getVisibility(),
                project.getBranches().stream().map(Branch::getName).toList()
        );
    }
}
