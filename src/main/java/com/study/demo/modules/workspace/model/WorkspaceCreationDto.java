package com.study.demo.modules.workspace.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class WorkspaceCreationDto {
    @Size(min = 3, message = "name must be longer than 3 characters")
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "owner id is required")
    private UUID owner;

    public @Size(min = 3, message = "name must be longer than 3 characters") @NotBlank(message = "name is required") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "name must be longer than 3 characters") @NotBlank(message = "name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "owner id is required") UUID getOwner() {
        return owner;
    }

    public void setOwner(@NotNull(message = "owner id is required") UUID owner) {
        this.owner = owner;
    }
}
