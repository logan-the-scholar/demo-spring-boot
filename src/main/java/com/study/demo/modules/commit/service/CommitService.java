package com.study.demo.modules.commit.service;

import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.user.model.User;

import java.util.List;
import java.util.UUID;

public interface CommitService {
    Commit createFromBase(Project project, User author);
    Commit createDraft(Project project, Branch branch);
    Commit findById(UUID commitId);
    List<Commit> findAllById(List<UUID> idLIst);
}
