package com.study.demo.modules.commit.service;

import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface CommitService {
    Commit createFromBase(ProjectModel project, UserModel author);
    Commit createDraft(ProjectModel project);
    Commit findById(UUID commitId);
    List<Commit> findAllById(List<UUID> idLIst);
}
