package com.study.demo.modules.branch.service;

import com.study.demo.modules.branch.model.BranchModel;
import com.study.demo.modules.branch.repository.BranchRepository;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private final BranchRepository repository;
    private final ProjectService projectService;

    public BranchServiceImpl(BranchRepository repository, ProjectService projectService) {
        this.repository = repository;
        this.projectService = projectService;
    }

    public void create(UUID projectId, String name) {
        try {
            ProjectModel project = projectService.findById(projectId);

            BranchModel branch = new BranchModel();
            branch.setName(name);
            branch.setProject(project);
            branch.setHeadCommit();

            repository.save(branch);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void createDefault(UUID projectId) {
        try {
            ProjectModel project = projectService.findById(projectId);



            BranchModel branch = new BranchModel();
            branch.setName("main");
            branch.setDefault(true);
            branch.setProject(project);

            repository.save(branch);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
