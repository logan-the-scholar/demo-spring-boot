package com.study.demo.modules.workspace.service;

import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.user.service.UserService;
import com.study.demo.modules.workspace.mapper.WorkspaceResponseMapper;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import com.study.demo.modules.workspace.repository.WorkspaceRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//@Component("workspaceService")
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private final WorkspaceRepository repository;

    //@Autowired
    private UserService userService;

    public WorkspaceServiceImpl(WorkspaceRepository repository) {
        this.repository = repository;
        //this.userService = userService;
    }

    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public WorkspaceModel createWorkspace(String name, UserModel owner) {
        WorkspaceModel createdWorkspace = new WorkspaceModel();
        createdWorkspace.setOwner(owner);
        createdWorkspace.setName(name);
        return this.repository.save(createdWorkspace);
    }

    public List<WorkspaceResponseMapper> getWorkspaces(UUID uuid) {
        try {
            UserModel owner = userService.getUserById(uuid);
            System.out.println(owner.getEmail());
            List<WorkspaceModel> workspaces = repository.findByOwner(owner);
            System.out.println(workspaces.getFirst().getName());

            if (workspaces.isEmpty()) {
                throw new RuntimeException("No workspaces found");

            } else {
                return workspaces.stream().map(WorkspaceResponseMapper::fromEntity).toList();//WorkspaceResponseMapper.fromEntity(workspaces);

            }

        } catch (BadRequestException e) {
            throw new RuntimeException(e);

        }
    }
}
