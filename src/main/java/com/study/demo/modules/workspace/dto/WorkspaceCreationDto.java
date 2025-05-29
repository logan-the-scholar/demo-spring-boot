package com.study.demo.modules.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class WorkspaceCreationDto {
    @Size(min = 3, message = "name must be longer than 3 characters")
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "visibility type is required")
    private WorkspaceVisibility visibility;

    @NotBlank(message = "owner uuid is required")
    private UUID owner;

    public @Size(min = 3, message = "name must be longer than 3 characters") @NotBlank(message = "name is required") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "name must be longer than 3 characters") @NotBlank(message = "name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "visibility type is required") WorkspaceVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(@NotBlank(message = "visibility type is required") WorkspaceVisibility visibility) {
        this.visibility = visibility;
    }

    public @NotBlank(message = "owner uuid is required") UUID getOwner() {
        return owner;
    }

    public void setOwner(@NotBlank(message = "owner uuid is required") UUID owner) {
        this.owner = owner;
    }
}
