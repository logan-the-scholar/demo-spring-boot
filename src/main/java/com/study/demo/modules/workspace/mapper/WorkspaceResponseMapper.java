package com.study.demo.modules.workspace.mapper;

import com.study.demo.modules.user.dto.UserRecurrence;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.workspace.model.WorkspaceModel;

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
