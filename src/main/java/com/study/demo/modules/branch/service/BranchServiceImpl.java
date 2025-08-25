package com.study.demo.modules.branch.service;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.branch.model.BranchResponseMapper;
import com.study.demo.modules.branch.repository.BranchRepository;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.commit.service.CommitService;
import com.study.demo.modules.file.model.FileResponseMapper;
import com.study.demo.modules.project.model.Project;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private final BranchRepository repository;
    private final CommitService commitService;

    public BranchServiceImpl(BranchRepository repository, CommitService commitService) {
        this.repository = repository;
        this.commitService = commitService;
    }

    public void create(Project repo, BranchCreationDto branch) {
        try {
            //ProjectModel project = projectService.findById(repo);
            Optional<Branch> fromBranch = this.findByProjectAndName(repo, branch.getFromBranch());
            if (fromBranch.isEmpty()) {
                throw new BadRequestException("(" + branch.getName() + ") branch origin not found for this project!");
            }

            Branch newBranch = new Branch();
            newBranch.setName(branch.getName());
            newBranch.setProject(repo);
            newBranch.setHeadCommit(fromBranch.get().getHeadCommit());

            newBranch.setDraftCommit(commitService.createDraft(repo, newBranch));

            repository.save(newBranch);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void createDefault(Project project) {
        try {

            Branch branch = new Branch();
            branch.setName("main");
            branch.setDefault(true);
            branch.setProject(project);

            Commit draftCommit = commitService.createDraft(project, branch);
            branch.setDraftCommit(draftCommit);
            branch.setHeadCommit(draftCommit);

            repository.save(branch);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Branch> findByProjectAndName(Project project, String name) {
        return repository.findByProjectAndName(project, name);
    }

    public BranchResponseMapper getFromHead(Project repo, String branch) throws BadRequestException {
        Branch fromBranch = this.findByProjectAndName(repo, branch).orElseThrow(() -> new BadRequestException(branch + " branch not found in this repository"));

        List<FileResponseMapper> headFiles = rebuildRepoAt(fromBranch.getHeadCommit().getId(), fromBranch.getDraftCommit().getId());
        return BranchResponseMapper.fromEntity(fromBranch, headFiles);
    }

    public List<FileResponseMapper> rebuildRepoAt(UUID commitId, UUID draftCommit) {
        System.out.println("--rebuild at: 0");
        List<Commit> history = getCommitHistory(commitId);
        Map<String, FileResponseMapper> repoState = new HashMap<>();
        System.out.println("--rebuild at: 1");

        if (history.isEmpty()) {
            return List.of();
        }

        history.forEach(c -> {
            c.getFiles().forEach(f -> {
                if (f.getCommit().getId().equals(c.getId())) {
                    repoState.put(f.getPath(), FileResponseMapper.fromEntity(f, draftCommit));
                }
            });
        });
        System.out.println("--rebuild at: 2");


        return repoState.values().stream().toList();
    }

    private List<Commit> getCommitHistory(UUID commitId) {

        List<Commit> history = new ArrayList<>();
        System.out.println("--get at: 0");

        Commit current = commitService.findById(commitId);
        System.out.println(current.getCreatedAt());

        while (current != null) {
            history.addFirst(current);
            current = current.getParent() != null ? current.getParent() : null;
            System.out.println(current);
        }

        System.out.println("--get at: 2");
        return history;
    }

}
