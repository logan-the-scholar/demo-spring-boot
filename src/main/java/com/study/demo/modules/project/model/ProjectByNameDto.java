package com.study.demo.modules.project.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ProjectByNameDto {
    @NotBlank(message = "Project name can't be empty")
    @Size(min = 2, message = "Project name must be between 2 - 24 characters", max = 24)
    private String name;

    @NotNull(message = "Workspace id is required")
    private UUID workspaceId;

    public @NotBlank(message = "Project name can't be empty") @Size(min = 2, message = "Project name must be between 2 - 24 characters", max = 24) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Project name can't be empty") @Size(min = 2, message = "Project name must be between 2 - 24 characters", max = 24) String name) {
        this.name = name;
    }

    public @NotNull(message = "Workspace id is required") UUID getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(@NotNull(message = "Workspace id is required") UUID workspaceId) {
        this.workspaceId = workspaceId;
    }
}
