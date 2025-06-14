package com.study.demo.modules.project.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ProjectCreationDto {

    @NotBlank(message = "Project name can't be empty")
    @Size(min = 2, message = "Project name must be between 2 - 24 characters", max = 24)
    private String name;

    @NotNull(message = "Project's main workspace id must be present")
    private UUID workspaceId;

    @NotNull(message = "Project visibility type is required -> public || private")
    private ProjectVisibility visibility;

    //TODO FROM EXISTING GITHUB REPOSITORY???
    //private List<FileModel> files = new ArrayList<>();

    public @NotBlank(message = "Project name can't be empty") @Size(min = 2, message = "Project name must be between 2 - 24 characters", max = 24) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Project name can't be empty") @Size(min = 2, message = "Project name must be between 2 - 24 characters", max = 24) String name) {
        this.name = name;
    }

    public @NotNull(message = "Project visibility type is required -> public || private") ProjectVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(@NotNull(message = "Project visibility type is required -> public || private") String visibility) {
        this.visibility =  ProjectVisibility.valueOf(visibility.toUpperCase());
    }

    public @NotNull(message = "Project's main workspace id must be present") UUID getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(@NotNull(message = "Project's main workspace id must be present") UUID workspaceId) {
        this.workspaceId = workspaceId;
    }
}
