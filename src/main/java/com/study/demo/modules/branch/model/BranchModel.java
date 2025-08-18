package com.study.demo.modules.branch.model;

import com.study.demo.modules.commit.model.CommitModel;
import com.study.demo.modules.project.model.ProjectModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "branch")
public class BranchModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProjectModel project;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @Column(name = "head_commit")
    private CommitModel headCommit;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    public BranchModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public CommitModel getHeadCommit() {
        return headCommit;
    }

    public void setHeadCommit(CommitModel headCommit) {
        this.headCommit = headCommit;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
