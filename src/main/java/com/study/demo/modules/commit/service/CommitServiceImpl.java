package com.study.demo.modules.commit.service;

import com.study.demo.common.exception.classes.ResourceNotFoundException;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.commit.repository.CommitRepository;
import com.study.demo.modules.file.service.FileVersionService;
import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommitServiceImpl implements CommitService {

    @Autowired
    private final CommitRepository repository;
    private final FileVersionService fileVersionService;

    public CommitServiceImpl(CommitRepository repository, FileVersionService fileVersionService) {
        this.repository = repository;
        this.fileVersionService = fileVersionService;
    }

    public Commit createFromBase(Project project, User author) {
        Commit initialCommit = new Commit();
        initialCommit.setStatus("drafted");
        initialCommit.setProject(project);
        initialCommit.setAuthor(author);
        repository.save(initialCommit);

//        project.getFiles().forEach((file) -> {
//            fileVersionService.create(file, initialCommit);
//        });

        return initialCommit;

    }

    public Commit createDraft(Project project, Branch branch) {
        Commit draftCommit = new Commit();
        draftCommit.setStatus("drafted");
        draftCommit.setProject(project);
        draftCommit.setBranch(branch);
        draftCommit.setCreatedAt(Instant.now().toEpochMilli());
        //repository.save(draftCommit);

        return draftCommit;
    }

    public Commit findById(UUID commitId) throws ResourceNotFoundException {
        Optional<Commit> commit =repository.findById(commitId);
        if(commit.isEmpty()) {
            throw new ResourceNotFoundException("Commit " + commitId + " not found");
        }
        return commit.get();
    }

    public List<Commit> findAllById(List<UUID> idLIst) {
        List<Commit> commitList = repository.findAllById(idLIst);
        if (commitList.isEmpty() ) {
            throw new ResourceNotFoundException("The commit list is empty");
        }
        return commitList;
    }
}
