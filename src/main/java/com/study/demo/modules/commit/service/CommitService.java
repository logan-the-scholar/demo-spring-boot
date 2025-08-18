package com.study.demo.modules.commit.service;

import com.study.demo.modules.commit.model.CommitModel;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;

public interface CommitService {
    CommitModel createFromBase(ProjectModel project, UserModel author);
}
