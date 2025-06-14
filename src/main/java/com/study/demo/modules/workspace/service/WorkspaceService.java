package com.study.demo.modules.workspace.service;

import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.workspace.mapper.WorkspaceResponseMapper;
import com.study.demo.modules.workspace.model.WorkspaceCreationDto;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    void createDefaultWorkspace(String name, UserModel owner);
    List<WorkspaceResponseMapper> getWorkspaces(UUID uuid);
    void createWorkspace(WorkspaceCreationDto workspace);
    WorkspaceModel getWorkspaceById(UUID id) throws BadRequestException;

}
