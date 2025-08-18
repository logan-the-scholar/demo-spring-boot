package com.study.demo.modules.commit.service;

import com.study.demo.modules.commit.model.CommitModel;
import com.study.demo.modules.commit.repository.CommitRepository;
import com.study.demo.modules.file.model.FileModel;
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

    public CommitModel createFromBase(ProjectModel project, UserModel author) {
        CommitModel initialCommit = new CommitModel();
        initialCommit.setStatus("drafted");
        initialCommit.setProject(project);
        initialCommit.setAuthor(author);
        repository.save(initialCommit);

        List<FileModel> files = project.getFiles();
        fileVersionService.create();

        return initialCommit;

    }
}
