package com.study.demo.modules.branch.service;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.branch.model.BranchResponseMapper;
import com.study.demo.modules.project.model.Project;
import org.apache.coyote.BadRequestException;

import java.util.Optional;

public interface BranchService {
    void create(Project repo, BranchCreationDto branchBody);
    void createDefault(Project project);
    Optional<Branch> findByProjectAndName(Project project, String name);
    BranchResponseMapper getFromHead(Project repo, String branch) throws BadRequestException;
}
