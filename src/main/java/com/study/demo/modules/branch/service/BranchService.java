package com.study.demo.modules.branch.service;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.branch.model.BranchResponseMapper;
import com.study.demo.modules.project.model.ProjectModel;
import org.apache.coyote.BadRequestException;

import java.util.Optional;
import java.util.UUID;

public interface BranchService {
    void create(UUID projectId, BranchCreationDto branchBody);
    void createDefault(UUID projectId);
    Optional<Branch> findByProjectAndName(ProjectModel project, String name);
    BranchResponseMapper getFromHead(UUID repoId, String branch) throws BadRequestException;
}
