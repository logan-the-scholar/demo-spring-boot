package com.study.demo.modules.project.service;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.model.BranchResponseMapper;
import com.study.demo.modules.project.model.ProjectResponseMapper;
import com.study.demo.modules.project.model.ProjectCreationDto;
import com.study.demo.modules.project.model.Project;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProjectService {
    List<ProjectResponseMapper> findAllById(UUID workspaceId);
    String create(ProjectCreationDto project);
    Project findById(UUID projectId) throws BadRequestException;
    Map<String, String> delete(UUID repoId) throws BadRequestException;
    ProjectResponseMapper get(UUID repoId);
    BranchResponseMapper getFromHead(UUID repoId, String branch);

    void createBranch(UUID repoId, BranchCreationDto branch);
}
