package com.study.demo.modules.commit.service;

import com.study.demo.common.exception.classes.ResourceNotFoundException;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.commit.repository.CommitRepository;
import com.study.demo.modules.file.service.FileVersionService;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Commit createFromBase(ProjectModel project, UserModel author) {
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

    public Commit createDraft(ProjectModel project) {
        Commit draftCommit = new Commit();
        draftCommit.setStatus("drafted");
        draftCommit.setProject(project);
        repository.save(draftCommit);

        return draftCommit;
    }

    public Commit findById(UUID commitId) {
        return repository.findById(commitId).orElseThrow(() -> new ResourceNotFoundException("Commit " + commitId + " not found"));
    }

    public List<Commit> findAllById(List<UUID> idLIst) {
        List<Commit> commitList = repository.findAllById(idLIst);
        if (commitList.isEmpty() ) {
            throw new ResourceNotFoundException("The commit list is empty");
        }
        return commitList;
    }
}
