package com.study.demo.modules.branch.model;

import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.project.model.ProjectModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProjectModel project;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @PrimaryKeyJoinColumn(name = "head_commit")
    private Commit headCommit;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn(name = "draft_commit")
    private Commit draftCommit;
    
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Commit> commits;

    public Branch() {
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

    public Commit getHeadCommit() {
        return headCommit;
    }

    public void setHeadCommit(Commit headCommit) {
        this.headCommit = headCommit;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Commit getDraftCommit() {
        return draftCommit;
    }

    public void setDraftCommit(Commit draftCommit) {
        this.draftCommit = draftCommit;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
