package com.study.demo.modules.workspace.service;

import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.workspace.mapper.WorkspaceResponseMapper;
import com.study.demo.modules.workspace.model.WorkspaceModel;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    public WorkspaceModel createWorkspace(String name, UserModel owner);

    public List<WorkspaceResponseMapper> getWorkspaces(UUID uuid);
}
