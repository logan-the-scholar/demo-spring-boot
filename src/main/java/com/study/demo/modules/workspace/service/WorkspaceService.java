package com.study.demo.modules.workspace.service;

import com.study.demo.modules.user.model.User;
import com.study.demo.modules.workspace.model.WorkspaceResponseMapper;
import com.study.demo.modules.workspace.model.WorkspaceCreationDto;
import com.study.demo.modules.workspace.model.WorkspaceModel;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    void createDefault(String name, User owner);
    List<WorkspaceResponseMapper> findAllById(UUID uuid);
    void create(WorkspaceCreationDto workspace);
    WorkspaceModel findById(UUID id);
}
