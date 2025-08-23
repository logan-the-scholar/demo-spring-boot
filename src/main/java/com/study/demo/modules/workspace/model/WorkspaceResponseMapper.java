package com.study.demo.modules.workspace.model;

import java.util.UUID;

public record WorkspaceResponseMapper(UUID id, String name, UUID owner) {

    public static WorkspaceResponseMapper fromEntity(WorkspaceModel workspace) {
        return new WorkspaceResponseMapper(
                workspace.getId(),
                workspace.getName(),
                workspace.getOwner().getId()
        );
    }
}
