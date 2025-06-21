package com.study.demo.modules.workspace.service;

import com.study.demo.common.exception.classes.EmptyResourcesException;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.user.service.UserService;
import com.study.demo.modules.workspace.mapper.WorkspaceResponseMapper;
import com.study.demo.modules.workspace.model.WorkspaceCreationDto;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import com.study.demo.modules.workspace.repository.WorkspaceRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private final WorkspaceRepository repository;

    private UserService userService;

    public WorkspaceServiceImpl(WorkspaceRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void createDefault(String name, UserModel owner) {
        WorkspaceModel createdWorkspace = new WorkspaceModel();
        createdWorkspace.setOwner(owner);
        createdWorkspace.setName(name);
        this.repository.save(createdWorkspace);
    }

    public List<WorkspaceResponseMapper> findAllById(UUID uuid) {
        try {
            UserModel owner = userService.getUserById(uuid);
            List<WorkspaceModel> workspaces = repository.findByOwner(owner);

            if (workspaces.isEmpty()) {
                throw new RuntimeException("No workspaces found");

            } else {
                return workspaces.stream().map(WorkspaceResponseMapper::fromEntity).toList();

            }

        } catch (BadRequestException e) {
            throw new RuntimeException(e);

        }
    }

    public void create(WorkspaceCreationDto workspace) {
        try {
            WorkspaceModel createdWorkspace = new WorkspaceModel();
            createdWorkspace.setOwner(userService.getUserById(workspace.getOwner()));
            createdWorkspace.setName(workspace.getName());
            this.repository.save(createdWorkspace);
            //"http://localhost:8080/demo/api/v0/workspace/{id}"
            //return URI();

        } catch (BadRequestException e) {
            throw new RuntimeException(e);

        }
    }

    public WorkspaceModel findById(UUID id) {
        Optional<WorkspaceModel> workspace = repository.findById(id);

        if (workspace.isPresent()) {
            return workspace.get();
        } else {
            throw new EmptyResourcesException("Workspace not found");
        }
    }

}
