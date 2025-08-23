package com.study.demo.modules.branch.service;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.branch.model.BranchResponseMapper;
import com.study.demo.modules.branch.repository.BranchRepository;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.commit.service.CommitService;
import com.study.demo.modules.file.model.FileResponseMapper;
import com.study.demo.modules.file.model.FileVersion;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.project.service.ProjectService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private final BranchRepository repository;
    private final ProjectService projectService;
    private final CommitService commitService;

    public BranchServiceImpl(BranchRepository repository, ProjectService projectService, CommitService commitService) {
        this.repository = repository;
        this.projectService = projectService;
        this.commitService = commitService;
    }

    public void create(UUID projectId, BranchCreationDto branch) {
        try {
            ProjectModel project = projectService.findById(projectId);
            Optional<Branch> fromBranch = this.findByProjectAndName(project, branch.getFromBranch());
            if (fromBranch.isEmpty()) {
                throw new BadRequestException("(" + branch.getName() + ") branch origin not found for this project!");
            }

            Branch newBranch = new Branch();
            newBranch.setName(branch.getName());
            newBranch.setProject(project);
            newBranch.setHeadCommit(fromBranch.get().getHeadCommit());

            newBranch.setDraftCommit(commitService.createDraft(project));

            repository.save(newBranch);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void createDefault(UUID projectId) {
        try {
            ProjectModel project = projectService.findById(projectId);

            Branch branch = new Branch();
            branch.setName("main");
            branch.setDefault(true);
            branch.setProject(project);

            repository.save(branch);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Branch> findByProjectAndName(ProjectModel project, String name) {
        return repository.findByProjectAndName(project, name);
    }

    public BranchResponseMapper getFromHead(UUID repoId, String branch) throws BadRequestException {
        ProjectModel project = projectService.findById(repoId);
        Branch fromBranch = this.findByProjectAndName(project, branch).orElseThrow(() -> new BadRequestException(branch + " branch not found in this repository"));

        List<Commit> commitList = fromBranch.getCommits().stream()
                .sorted(Comparator.comparingLong(Commit::getCreatedAt)).toList();

        List<FileResponseMapper> headFiles = rebuildRepoAt(fromBranch.getHeadCommit().getId(), fromBranch.getDraftCommit().getId());

        return BranchResponseMapper.fromEntity(fromBranch, headFiles);
    }

    public List<FileResponseMapper> rebuildRepoAt(UUID commitId, UUID draftCommit) {
        List<Commit> history = getCommitHistory(commitId);
        Map<String, FileResponseMapper> repoState = new HashMap<>();

        history.forEach(c -> {
            c.getFiles().forEach(f -> {
                if (f.getCommit().getId().equals(c.getId())) {
                    repoState.put(f.getPath(), FileResponseMapper.fromEntity(f, draftCommit));
                }
            });
        });

        return repoState.values().stream().toList();
    }

    private List<Commit> getCommitHistory(UUID commitId) {
        List<Commit> history = new ArrayList<>();
        Commit current = commitService.findById(commitId);

        while (current != null) {
            history.addFirst(current);
            current = current.getParent().getId() != null ? commitService.findById(current.getBranch().getId()) : null;
        }

        return history;
    }

}
